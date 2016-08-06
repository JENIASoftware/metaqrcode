/*
 * Copyright 2016 JENIA Software.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.jenia.metaqrcode.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 
 * Userfull methods for marshalling and unmarshalling of a DTO.
 * All DTO MUST extends this class to have these usefull methods
 * 
 * @author andreatessaro
 *
 */
public class Dto {

	/**
	 * 
	 * Will marshall this instance into XML format.
	 * Marshalling will be done inside input OS
	 * 
	 * @param os output stream for marshalling
	 * @throws JAXBException if marshalling has some error
	 */
	public void toXML(OutputStream os) throws JAXBException {
		JAXBContext jcupr = JAXBContext.newInstance(this.getClass());
		Marshaller m = jcupr.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(this, os);
	}

	/**
	 * 
	 * Will marshall this instance into XML format.
	 * Marshalling will be done and returned as String
	 * 
	 * @return the XML of this DTO
	 * @throws JAXBException if marshalling has some error
	 */
	public String toXML() throws JAXBException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		toXML(baos);
		try {
			baos.flush();
			baos.close();
		} catch (IOException e) {
			// how can be possible???
		}
		return new String(baos.toByteArray());
	}

	/**
	 * 
	 * Will marshall this instance into JSON format.
	 * Marshalling will be done inside input OS
	 * 
	 * @param os output stream for marshalling
	 * @throws JAXBException if marshalling has some error
	 */
	public void toJson(OutputStream os) throws JAXBException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(org.codehaus.jackson.map.SerializationConfig.Feature.WRAP_ROOT_VALUE, false);		
		try {
			mapper.writeValue(os, this);
		} catch (JsonGenerationException e) {
			throw new JAXBException("Unable to generate json message from dto",e);
		} catch (JsonMappingException e) {
			throw new JAXBException("Unable to generate json message from dto",e);
		} catch (IOException e) {
			throw new JAXBException("Unable to generate json message from dto",e);
		}
	}

	/**
	 * 
	 * Will marshall this instance into JSON format.
	 * Marshalling will be done and returned as String
	 * 
	 * @return the JSON of this DTO
	 * @throws JAXBException if marshalling has some error
	 */
	public String toJson() throws JAXBException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		toJson(baos);
		try {
			baos.flush();
			baos.close();
		} catch (IOException e) {
			// how can be possible???
		}
		return new String(baos.toByteArray());
	}

	/**
	 * 
	 * will unmarshall an XML String returning received class
	 * 
	 * @param s the XML String
	 * @param clazz the class that will be returned
	 * @return The unmarshalled class
	 * @throws JAXBException in unmarshalling has some error
	 */
	public static Dto fromXML(String s, Class<? extends Dto> clazz) throws JAXBException {
		ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
		return fromXML(bais, clazz);
	}
	
	/**
	 * 
	 * will unmarshall an XML String returning received class
	 * 
	 * @param is the XML InputStream
	 * @param clazz the class that will be returned
	 * @return The unmarshalled class
	 * @throws JAXBException in unmarshalling has some error
	 */
	public static Dto fromXML(InputStream is, Class<? extends Dto> clazz) throws JAXBException {
		JAXBContext jcupr = JAXBContext.newInstance(clazz);
		Unmarshaller u = jcupr.createUnmarshaller();
		return (Dto) u.unmarshal(is);
	}

	/**
	 * 
	 * will unmarshall a JSON String returning received class
	 * 
	 * @param s the JSON String
	 * @param clazz the class that will be returned
	 * @return The unmarshalled class
	 * @throws JAXBException in unmarshalling has some error
	 */
	public static Dto fromJson(String s, Class<? extends Dto> clazz) throws JAXBException {
		ByteArrayInputStream bais = new ByteArrayInputStream(s.getBytes());
		return fromJson(bais, clazz);
	}
	
	/**
	 * 
	 * will unmarshall a JSON String returning received class
	 * 
	 * @param is the JSON InputStream
	 * @param clazz the class that will be returned
	 * @return The unmarshalled class
	 * @throws JAXBException in unmarshalling has some error
	 */
	public static Dto fromJson(InputStream is, Class<? extends Dto> clazz) throws JAXBException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, false);		
		try {
			return (Dto)mapper.readValue(is, clazz);
		} catch (JsonParseException e) {
			throw new JAXBException("Unable to parse json message into dto",e);
		} catch (JsonMappingException e) {
			throw new JAXBException("Unable to parse json message into dto",e);
		} catch (IOException e) {
			throw new JAXBException("Unable to parse json message into dto",e);
		}
	}
}
