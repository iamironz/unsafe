##Android unsafe wrapper
Documented android unsafe wrapper for using internal `sun.misc.Unsafe` api.
Android has no `sun.misc.Unsafe` class in standard sdk library. 
This library solve this problem by using compiled direct access to system class library.
Use 

For more explanation see this articles list:


1. https://dzone.com/articles/understanding-sunmiscunsafe
2. http://mishadoff.com/blog/java-magic-part-4-sun-dot-misc-dot-unsafe
3. https://www.youtube.com/watch?v=FwTkFqJfW8U


Adding in project:
------------------
For using in your own projects just add jcenter repository superset:

```groovy
repositories {
    jcenter()
}
```

And after it you should just add as compile dependency:

```groovy
compile 'com.implimentz:unsafe:0.0.2'
```

Using in project:
-----------------

You should use `UnsafeAndroid` library wrapper class for `Unsafe` class access:

```java
final UnsafeAndroid unsafe = new UnsafeAndroid();
final Bitmap bitmap = unsafe.allocateInstance(Bitmap.class);
```