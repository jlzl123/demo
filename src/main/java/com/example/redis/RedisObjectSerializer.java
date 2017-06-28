package com.example.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
//redis对象的序列化和反序列化
public class RedisObjectSerializer implements RedisSerializer<Object>{
	private Converter<Object, byte[]> serializer=new SerializingConverter();
	private Converter<byte[], Object> deserializer=new DeserializingConverter();

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		// TODO Auto-generated method stub
		if(bytes!=null&&bytes.length>0){
			return deserializer.convert(bytes);
		}
		return null;
	}

	@Override
	public byte[] serialize(Object object) throws SerializationException {
		// TODO Auto-generated method stub
		if(object!=null){
			return serializer.convert(object);
		}
		return new byte[0];
	}

}
