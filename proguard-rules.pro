-keepattributes Exceptions,Signature,InnerClasses,SourceFile,*Annotation*,EnclosingMethod
-keep class org.apache.commons.lang3.ObjectUtils  {
    public static ? clone(java.lang.Object);
}
-keep class org.apache.commons.lang3.**  { *; }
-keep class org.apache.commons.text.**  { *; }
-keep class com.fasterxml.jackson.core.util.VersionUtil {
    public VersionUtil();
}
-keep class com.fasterxml.jackson.databind.jdk14.JDK14Util$RecordAccessor {
    public ? getType();
}
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.Nonnull
-dontwarn javax.annotation.meta.TypeQualifierDefault
-dontwarn javax.annotation.concurrent.GuardedBy