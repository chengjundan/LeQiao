package com.lizi.boot.Util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.lizi.boot.Bean.RpcRequest;
import com.lizi.boot.Bean.RpcResponse;
import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;

@Slf4j
public class KryoSerializer extends Serializer {

    private final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        return kryo;
    });

    public byte[] serialize(Object obj){
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Output output = new Output(byteArrayOutputStream)) {
            return output.toBytes();
        }catch (Exception e){
            throw new SerializationException("Serialization failed");
        }
    }

    @Override
    public void write(Kryo kryo, Output output, Object o) {

    }

    @Override
    public Object read(Kryo kryo, Input input, Class aClass) {
        return null;
    }
}
