package com.study.beans;

public class Department {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_dept.id
     *
     * @mbggenerated Sat Feb 27 19:38:33 CST 2021
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_dept.deptName
     *
     * @mbggenerated Sat Feb 27 19:38:33 CST 2021
     */
    private String deptname;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_dept.id
     *
     * @return the value of tbl_dept.id
     *
     * @mbggenerated Sat Feb 27 19:38:33 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_dept.id
     *
     * @param id the value for tbl_dept.id
     *
     * @mbggenerated Sat Feb 27 19:38:33 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_dept.deptName
     *
     * @return the value of tbl_dept.deptName
     *
     * @mbggenerated Sat Feb 27 19:38:33 CST 2021
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_dept.deptName
     *
     * @param deptname the value for tbl_dept.deptName
     *
     * @mbggenerated Sat Feb 27 19:38:33 CST 2021
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }
}