package cn.calfgz.study.rabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Order implements Serializable {

	private String id;
	private String name;
	
}
