package ren.xiangmu.creatorapi.beans;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.stereotype.Component;

import ren.xiangmu.creatorapi.models.User;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
@Component
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@XmlSeeAlso({ArrayList.class, User.class})
public class ResponseMessage<T> {

	//编码
	private int code;

	//信息描述
	private String msg;

	//具体的信息内容
	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
