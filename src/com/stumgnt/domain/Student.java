package com.stumgnt.domain;

public class Student {
	
	/* 
	 * The fields of <code>Students</code> must exactly the same as the columns'
	 * name in the database. Otherwise, SQL may not get value.
	 */
	private String stuname, gender, phone, hobby, comments;
	private int stuid, stuage;

	/**
	 * Default constructor.
	 * Without this constructor, some servlet cannot create collection 
	 * using <code>Student</code> generic.
	 * 
	 */
	public Student() {
		super();
	}
	/**
	 * The name, age, and gender of a student are mandatory attributes for a student
	 * And chain to the full-arg constructor, with the other attributes blank.
	 *  
	 * @param name student's name, limit to 20 characters
	 * @param age student's age
	 * @param gender student's gender, should be male, female, or other
	 */
	public Student(String name, int age, String gender) {
		this(name, age, gender, "","","");
	}

	/**
	 * Full-arg constructor of a student.
	 * @param name student's name, limit to 20 characters
	 * @param age student's age
	 * @param gender student's gender, should be male, female, or other
	 * @param phone contact information of a student, limit 20 character
	 * @param hobby student's hobby, limit to 50 characters.
	 * @param comments Other information about student, add manually,
	 * 			limit to 200 characters.
	 */
	public Student(String name, int age, String gender, String phone, String hobby, String comments) {
		this.stuname = name;
		this.stuage = age;
		this.gender = gender;
		this.phone = phone;
		this.hobby = hobby;
		this.comments = comments;
	}
	
	public Student(int id, String name, int age, String gender, String phone, String hobby, String comments) {
		this.stuid = id;
		this.stuname = name;
		this.stuage = age;
		this.gender = gender;
		this.phone = phone;
		this.hobby = hobby;
		this.comments = comments;
	}


	/**
	 * Getter and setter for all fields.
	 * Notice that the method name must exactly the same as the fields.
	 * For example, must be <code>getStuname()</code>, not getName.
	 */
	public String getStuname() {
		return stuname;
	}

	public void setStuname(String name) {
		this.stuname = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int id) {
		this.stuid = id;
	}

	public int getStuage() {
		return stuage;
	}

	public void setStuage(int age) {
		this.stuage = age;
	}

}
