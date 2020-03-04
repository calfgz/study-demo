package cn.calfgz.study.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

	private String id;
	
	private String name;
	
	private String content;
}
