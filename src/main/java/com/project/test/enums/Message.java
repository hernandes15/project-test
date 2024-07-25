package com.project.test.enums;


public enum Message {
	  SUCCESS("Success!"),
	  NOTFOUND("Data Not Found!"),
	  ERROR("Error in services!"),
	  CREATED("Data successfully created!"),
	  UPDATED("Data successfully updated!"),
	  USED("Username already used!"),
	  DELETED("Data succesfully deleted!"),
	  BADREQUEST("Bad Request!"),
	  BADDATE("{data} Tanggal {date} belum tersedia!");

	  private final String value;

	  private Message(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return value;
	  }
}
