package com.charles.test.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;

public class ObjectSerializeAndDeserialize {
	
	public static void main(String[] args) {
		SerializePerson();
		Person p = DeserializePerson();
		System.out.println(MessageFormat.format("name={0}, age={1}, sex={2}", p.getName(), p.getAge(), p.getSex()));
	}
	
	private static void SerializePerson() {
		Person person = new Person();
		person.setName("puremancw");
		person.setAge(30);
		person.setSex("male");
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
					new File("D:/Person.txt")));
			oos.writeObject(person);
			System.out.println("Person对象序列化成功！");
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static Person DeserializePerson() {
		Person person = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					new File("D:/Person.txt")));
			person = (Person) ois.readObject();
			System.out.println("Person对象反序列化成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}
}
