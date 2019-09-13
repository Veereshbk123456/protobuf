#!/usr/bin/env bash
echo 'starting code generation'

protoc --proto_path=D:\ProtoBufToolEncoder\src\main\resources  --java_out=D:\ProtoBufToolEncoder\src\main\java D:\ProtoBufToolEncoder\src\main\resources\School.proto

