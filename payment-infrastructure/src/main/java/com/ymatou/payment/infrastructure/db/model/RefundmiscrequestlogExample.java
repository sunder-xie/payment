package com.ymatou.payment.infrastructure.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RefundmiscrequestlogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public RefundmiscrequestlogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
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

        public Criteria andLogidIsNull() {
            addCriterion("LogId is null");
            return (Criteria) this;
        }

        public Criteria andLogidIsNotNull() {
            addCriterion("LogId is not null");
            return (Criteria) this;
        }

        public Criteria andLogidEqualTo(String value) {
            addCriterion("LogId =", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotEqualTo(String value) {
            addCriterion("LogId <>", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThan(String value) {
            addCriterion("LogId >", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThanOrEqualTo(String value) {
            addCriterion("LogId >=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThan(String value) {
            addCriterion("LogId <", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThanOrEqualTo(String value) {
            addCriterion("LogId <=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLike(String value) {
            addCriterion("LogId like", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotLike(String value) {
            addCriterion("LogId not like", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidIn(List<String> values) {
            addCriterion("LogId in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotIn(List<String> values) {
            addCriterion("LogId not in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidBetween(String value1, String value2) {
            addCriterion("LogId between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotBetween(String value1, String value2) {
            addCriterion("LogId not between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidIsNull() {
            addCriterion("CorrelateId is null");
            return (Criteria) this;
        }

        public Criteria andCorrelateidIsNotNull() {
            addCriterion("CorrelateId is not null");
            return (Criteria) this;
        }

        public Criteria andCorrelateidEqualTo(String value) {
            addCriterion("CorrelateId =", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidNotEqualTo(String value) {
            addCriterion("CorrelateId <>", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidGreaterThan(String value) {
            addCriterion("CorrelateId >", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidGreaterThanOrEqualTo(String value) {
            addCriterion("CorrelateId >=", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidLessThan(String value) {
            addCriterion("CorrelateId <", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidLessThanOrEqualTo(String value) {
            addCriterion("CorrelateId <=", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidLike(String value) {
            addCriterion("CorrelateId like", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidNotLike(String value) {
            addCriterion("CorrelateId not like", value, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidIn(List<String> values) {
            addCriterion("CorrelateId in", values, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidNotIn(List<String> values) {
            addCriterion("CorrelateId not in", values, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidBetween(String value1, String value2) {
            addCriterion("CorrelateId between", value1, value2, "correlateid");
            return (Criteria) this;
        }

        public Criteria andCorrelateidNotBetween(String value1, String value2) {
            addCriterion("CorrelateId not between", value1, value2, "correlateid");
            return (Criteria) this;
        }

        public Criteria andMethodIsNull() {
            addCriterion("Method is null");
            return (Criteria) this;
        }

        public Criteria andMethodIsNotNull() {
            addCriterion("Method is not null");
            return (Criteria) this;
        }

        public Criteria andMethodEqualTo(String value) {
            addCriterion("Method =", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotEqualTo(String value) {
            addCriterion("Method <>", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThan(String value) {
            addCriterion("Method >", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodGreaterThanOrEqualTo(String value) {
            addCriterion("Method >=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThan(String value) {
            addCriterion("Method <", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLessThanOrEqualTo(String value) {
            addCriterion("Method <=", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodLike(String value) {
            addCriterion("Method like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotLike(String value) {
            addCriterion("Method not like", value, "method");
            return (Criteria) this;
        }

        public Criteria andMethodIn(List<String> values) {
            addCriterion("Method in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotIn(List<String> values) {
            addCriterion("Method not in", values, "method");
            return (Criteria) this;
        }

        public Criteria andMethodBetween(String value1, String value2) {
            addCriterion("Method between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andMethodNotBetween(String value1, String value2) {
            addCriterion("Method not between", value1, value2, "method");
            return (Criteria) this;
        }

        public Criteria andRequesttimeIsNull() {
            addCriterion("RequestTime is null");
            return (Criteria) this;
        }

        public Criteria andRequesttimeIsNotNull() {
            addCriterion("RequestTime is not null");
            return (Criteria) this;
        }

        public Criteria andRequesttimeEqualTo(Date value) {
            addCriterion("RequestTime =", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeNotEqualTo(Date value) {
            addCriterion("RequestTime <>", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeGreaterThan(Date value) {
            addCriterion("RequestTime >", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RequestTime >=", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeLessThan(Date value) {
            addCriterion("RequestTime <", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeLessThanOrEqualTo(Date value) {
            addCriterion("RequestTime <=", value, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeIn(List<Date> values) {
            addCriterion("RequestTime in", values, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeNotIn(List<Date> values) {
            addCriterion("RequestTime not in", values, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeBetween(Date value1, Date value2) {
            addCriterion("RequestTime between", value1, value2, "requesttime");
            return (Criteria) this;
        }

        public Criteria andRequesttimeNotBetween(Date value1, Date value2) {
            addCriterion("RequestTime not between", value1, value2, "requesttime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeIsNull() {
            addCriterion("ResponseTime is null");
            return (Criteria) this;
        }

        public Criteria andResponsetimeIsNotNull() {
            addCriterion("ResponseTime is not null");
            return (Criteria) this;
        }

        public Criteria andResponsetimeEqualTo(Date value) {
            addCriterion("ResponseTime =", value, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeNotEqualTo(Date value) {
            addCriterion("ResponseTime <>", value, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeGreaterThan(Date value) {
            addCriterion("ResponseTime >", value, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ResponseTime >=", value, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeLessThan(Date value) {
            addCriterion("ResponseTime <", value, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeLessThanOrEqualTo(Date value) {
            addCriterion("ResponseTime <=", value, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeIn(List<Date> values) {
            addCriterion("ResponseTime in", values, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeNotIn(List<Date> values) {
            addCriterion("ResponseTime not in", values, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeBetween(Date value1, Date value2) {
            addCriterion("ResponseTime between", value1, value2, "responsetime");
            return (Criteria) this;
        }

        public Criteria andResponsetimeNotBetween(Date value1, Date value2) {
            addCriterion("ResponseTime not between", value1, value2, "responsetime");
            return (Criteria) this;
        }

        public Criteria andIsexceptionIsNull() {
            addCriterion("IsException is null");
            return (Criteria) this;
        }

        public Criteria andIsexceptionIsNotNull() {
            addCriterion("IsException is not null");
            return (Criteria) this;
        }

        public Criteria andIsexceptionEqualTo(Boolean value) {
            addCriterion("IsException =", value, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionNotEqualTo(Boolean value) {
            addCriterion("IsException <>", value, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionGreaterThan(Boolean value) {
            addCriterion("IsException >", value, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IsException >=", value, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionLessThan(Boolean value) {
            addCriterion("IsException <", value, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionLessThanOrEqualTo(Boolean value) {
            addCriterion("IsException <=", value, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionIn(List<Boolean> values) {
            addCriterion("IsException in", values, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionNotIn(List<Boolean> values) {
            addCriterion("IsException not in", values, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionBetween(Boolean value1, Boolean value2) {
            addCriterion("IsException between", value1, value2, "isexception");
            return (Criteria) this;
        }

        public Criteria andIsexceptionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IsException not between", value1, value2, "isexception");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoIsNull() {
            addCriterion("RefundBatchNo is null");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoIsNotNull() {
            addCriterion("RefundBatchNo is not null");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoEqualTo(String value) {
            addCriterion("RefundBatchNo =", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoNotEqualTo(String value) {
            addCriterion("RefundBatchNo <>", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoGreaterThan(String value) {
            addCriterion("RefundBatchNo >", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoGreaterThanOrEqualTo(String value) {
            addCriterion("RefundBatchNo >=", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoLessThan(String value) {
            addCriterion("RefundBatchNo <", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoLessThanOrEqualTo(String value) {
            addCriterion("RefundBatchNo <=", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoLike(String value) {
            addCriterion("RefundBatchNo like", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoNotLike(String value) {
            addCriterion("RefundBatchNo not like", value, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoIn(List<String> values) {
            addCriterion("RefundBatchNo in", values, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoNotIn(List<String> values) {
            addCriterion("RefundBatchNo not in", values, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoBetween(String value1, String value2) {
            addCriterion("RefundBatchNo between", value1, value2, "refundbatchno");
            return (Criteria) this;
        }

        public Criteria andRefundbatchnoNotBetween(String value1, String value2) {
            addCriterion("RefundBatchNo not between", value1, value2, "refundbatchno");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated do_not_delete_during_merge Wed May 18 17:28:11 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RefundMiscRequestLog
     *
     * @mbggenerated Wed May 18 17:28:11 CST 2016
     */
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