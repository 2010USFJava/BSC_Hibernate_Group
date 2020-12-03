package com.revature.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"books"})
public class Library {

	@Id
	@Column
	private String name;
	
	@Size(min = 1, max = 100)
	@NotNull
	@Column(length = 100, nullable = false)
	private String location;
	
	@JsonBackReference
	private List<Book> books;
}
