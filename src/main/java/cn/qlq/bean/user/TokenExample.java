package cn.qlq.bean.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TokenExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TokenExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andTokenstrIsNull() {
            addCriterion("tokenStr is null");
            return (Criteria) this;
        }

        public Criteria andTokenstrIsNotNull() {
            addCriterion("tokenStr is not null");
            return (Criteria) this;
        }

        public Criteria andTokenstrEqualTo(String value) {
            addCriterion("tokenStr =", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrNotEqualTo(String value) {
            addCriterion("tokenStr <>", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrGreaterThan(String value) {
            addCriterion("tokenStr >", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrGreaterThanOrEqualTo(String value) {
            addCriterion("tokenStr >=", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrLessThan(String value) {
            addCriterion("tokenStr <", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrLessThanOrEqualTo(String value) {
            addCriterion("tokenStr <=", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrLike(String value) {
            addCriterion("tokenStr like", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrNotLike(String value) {
            addCriterion("tokenStr not like", value, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrIn(List<String> values) {
            addCriterion("tokenStr in", values, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrNotIn(List<String> values) {
            addCriterion("tokenStr not in", values, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrBetween(String value1, String value2) {
            addCriterion("tokenStr between", value1, value2, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andTokenstrNotBetween(String value1, String value2) {
            addCriterion("tokenStr not between", value1, value2, "tokenstr");
            return (Criteria) this;
        }

        public Criteria andLosetimeIsNull() {
            addCriterion("losetime is null");
            return (Criteria) this;
        }

        public Criteria andLosetimeIsNotNull() {
            addCriterion("losetime is not null");
            return (Criteria) this;
        }

        public Criteria andLosetimeEqualTo(Date value) {
            addCriterion("losetime =", value, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeNotEqualTo(Date value) {
            addCriterion("losetime <>", value, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeGreaterThan(Date value) {
            addCriterion("losetime >", value, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("losetime >=", value, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeLessThan(Date value) {
            addCriterion("losetime <", value, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeLessThanOrEqualTo(Date value) {
            addCriterion("losetime <=", value, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeIn(List<Date> values) {
            addCriterion("losetime in", values, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeNotIn(List<Date> values) {
            addCriterion("losetime not in", values, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeBetween(Date value1, Date value2) {
            addCriterion("losetime between", value1, value2, "losetime");
            return (Criteria) this;
        }

        public Criteria andLosetimeNotBetween(Date value1, Date value2) {
            addCriterion("losetime not between", value1, value2, "losetime");
            return (Criteria) this;
        }

        public Criteria andUserfullnameIsNull() {
            addCriterion("userfullname is null");
            return (Criteria) this;
        }

        public Criteria andUserfullnameIsNotNull() {
            addCriterion("userfullname is not null");
            return (Criteria) this;
        }

        public Criteria andUserfullnameEqualTo(String value) {
            addCriterion("userfullname =", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameNotEqualTo(String value) {
            addCriterion("userfullname <>", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameGreaterThan(String value) {
            addCriterion("userfullname >", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameGreaterThanOrEqualTo(String value) {
            addCriterion("userfullname >=", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameLessThan(String value) {
            addCriterion("userfullname <", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameLessThanOrEqualTo(String value) {
            addCriterion("userfullname <=", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameLike(String value) {
            addCriterion("userfullname like", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameNotLike(String value) {
            addCriterion("userfullname not like", value, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameIn(List<String> values) {
            addCriterion("userfullname in", values, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameNotIn(List<String> values) {
            addCriterion("userfullname not in", values, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameBetween(String value1, String value2) {
            addCriterion("userfullname between", value1, value2, "userfullname");
            return (Criteria) this;
        }

        public Criteria andUserfullnameNotBetween(String value1, String value2) {
            addCriterion("userfullname not between", value1, value2, "userfullname");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}