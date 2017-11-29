package com.xiaoshu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttachmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttachmentExample() {
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

        public Criteria andAttachmentidIsNull() {
            addCriterion("attachmentId is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentidIsNotNull() {
            addCriterion("attachmentId is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentidEqualTo(Integer value) {
            addCriterion("attachmentId =", value, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidNotEqualTo(Integer value) {
            addCriterion("attachmentId <>", value, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidGreaterThan(Integer value) {
            addCriterion("attachmentId >", value, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("attachmentId >=", value, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidLessThan(Integer value) {
            addCriterion("attachmentId <", value, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidLessThanOrEqualTo(Integer value) {
            addCriterion("attachmentId <=", value, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidIn(List<Integer> values) {
            addCriterion("attachmentId in", values, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidNotIn(List<Integer> values) {
            addCriterion("attachmentId not in", values, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidBetween(Integer value1, Integer value2) {
            addCriterion("attachmentId between", value1, value2, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentidNotBetween(Integer value1, Integer value2) {
            addCriterion("attachmentId not between", value1, value2, "attachmentid");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameIsNull() {
            addCriterion("attachmentName is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameIsNotNull() {
            addCriterion("attachmentName is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameEqualTo(String value) {
            addCriterion("attachmentName =", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameNotEqualTo(String value) {
            addCriterion("attachmentName <>", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameGreaterThan(String value) {
            addCriterion("attachmentName >", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameGreaterThanOrEqualTo(String value) {
            addCriterion("attachmentName >=", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameLessThan(String value) {
            addCriterion("attachmentName <", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameLessThanOrEqualTo(String value) {
            addCriterion("attachmentName <=", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameLike(String value) {
            addCriterion("attachmentName like", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameNotLike(String value) {
            addCriterion("attachmentName not like", value, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameIn(List<String> values) {
            addCriterion("attachmentName in", values, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameNotIn(List<String> values) {
            addCriterion("attachmentName not in", values, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameBetween(String value1, String value2) {
            addCriterion("attachmentName between", value1, value2, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentnameNotBetween(String value1, String value2) {
            addCriterion("attachmentName not between", value1, value2, "attachmentname");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathIsNull() {
            addCriterion("attachmentPath is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathIsNotNull() {
            addCriterion("attachmentPath is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathEqualTo(String value) {
            addCriterion("attachmentPath =", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathNotEqualTo(String value) {
            addCriterion("attachmentPath <>", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathGreaterThan(String value) {
            addCriterion("attachmentPath >", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathGreaterThanOrEqualTo(String value) {
            addCriterion("attachmentPath >=", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathLessThan(String value) {
            addCriterion("attachmentPath <", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathLessThanOrEqualTo(String value) {
            addCriterion("attachmentPath <=", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathLike(String value) {
            addCriterion("attachmentPath like", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathNotLike(String value) {
            addCriterion("attachmentPath not like", value, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathIn(List<String> values) {
            addCriterion("attachmentPath in", values, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathNotIn(List<String> values) {
            addCriterion("attachmentPath not in", values, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathBetween(String value1, String value2) {
            addCriterion("attachmentPath between", value1, value2, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmentpathNotBetween(String value1, String value2) {
            addCriterion("attachmentPath not between", value1, value2, "attachmentpath");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeIsNull() {
            addCriterion("attachmentTime is null");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeIsNotNull() {
            addCriterion("attachmentTime is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeEqualTo(Date value) {
            addCriterion("attachmentTime =", value, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeNotEqualTo(Date value) {
            addCriterion("attachmentTime <>", value, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeGreaterThan(Date value) {
            addCriterion("attachmentTime >", value, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("attachmentTime >=", value, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeLessThan(Date value) {
            addCriterion("attachmentTime <", value, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeLessThanOrEqualTo(Date value) {
            addCriterion("attachmentTime <=", value, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeIn(List<Date> values) {
            addCriterion("attachmentTime in", values, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeNotIn(List<Date> values) {
            addCriterion("attachmentTime not in", values, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeBetween(Date value1, Date value2) {
            addCriterion("attachmentTime between", value1, value2, "attachmenttime");
            return (Criteria) this;
        }

        public Criteria andAttachmenttimeNotBetween(Date value1, Date value2) {
            addCriterion("attachmentTime not between", value1, value2, "attachmenttime");
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