// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: School.proto

package app;

public final class SchoolOuterClass {
  private SchoolOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_School_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_School_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014School.proto\"P\n\006School\022\014\n\004Name\030\001 \001(\t\022\022" +
      "\n\nEmployeeId\030\002 \001(\t\022\023\n\013Designation\030\003 \001(\t\022" +
      "\017\n\007Company\030\004 \001(\tB\007\n\003appP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_School_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_School_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_School_descriptor,
        new java.lang.String[] { "Name", "EmployeeId", "Designation", "Company", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
