package cn.qlq.websocket;

import java.io.IOException;
import java.util.Hashtable;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint("/chat")
@Component
public class ChatServer {

	private boolean first = true;

	private String name;

	private Session session;

	private static final Hashtable<String, ChatServer> clientSet = new Hashtable<String, ChatServer>();

	public ChatServer() {
		System.out.println("========ChatServer创建============");
	}

	/*
	 * 客户端连接时触发该方法
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException {
		this.session = session;
	}

	/*
	 * 客户端断开时触发该方法
	 */
	@OnClose
	public void onClose() {
		clientSet.remove(name);

		String message = String.format("【%s %s】", name, "离开了聊天室！");
		broadcast(message);
	}

	/*
	 * 客户端收到消息时触发该方法
	 */
	@OnMessage
	public void onMessage(String msg) {
		if (first) {
			name = msg;
			clientSet.put(name, this);
			String message = String.format("【%s %s】", name, "加入了聊天室！");
			// 发送消息
			broadcast(message);
			first = false;
		} else {
			broadcast("【" + name + "】" + ":" + msg);
		}

	}

	// 当客户端通信出现错误时，激发该方法
	@OnError
	public void onError(Throwable t) throws Throwable {
		System.out.println("WebSocket服务端错误 " + t);
	}

	/**
	 * 将此方法设计为静态方法用于服务器主动调用该方法广播消息
	 * 
	 * @param msg
	 */
	public static void broadcast(String msg) {
		// 遍历服务器关联的所有客户端
		ChatServer client = null;
		for (String nickname : clientSet.keySet()) {

			try {
				client = (ChatServer) clientSet.get(nickname);
				synchronized (client) {
					// 发送消息
					client.session.getBasicRemote().sendText(msg);
				}
			} catch (IOException e) {
				System.out.println("聊天错误，向客户端 " + client + " 发送消息出现错误。");
				clientSet.remove(nickname);
				try {
					client.session.close();
				} catch (IOException e1) {
				}
				String message = String.format("【%s %s】", client.name, "已经被断开了连接。");
				broadcast(message);
			}
		}
	}

}