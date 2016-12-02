/*
 * Copyright (C) 2007 The Android Open Source Project
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

package com.ironz.unsafe;

import android.annotation.TargetApi;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Alexander Efremenkov.
 * Date: 13.08.16, 9:32
 * email: implimentz@gmail.com
 * twitter: iamironz
 * <p>
 * The package name notwithstanding, this class is the quasi-standard
 * way for Java code to gain access to and use functionality which,
 * when unsupervised, would allow one to break the pointer/type safety
 * of Java.
 */

@SuppressWarnings({"unchecked", "unused", "WeakerAccess"})
public class UnsafeAndroid {

    private static final Unsafe unsafe;

    static {
        try {
            final Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Allocates an instance of the given class without running the constructor.
     * The class' will be run, if necessary.
     */
    public <T> T allocateInstance(Class<T> clazz) throws InstantiationException {
        return (T) unsafe.allocateInstance(clazz);
    }

    /**
     * Gets the offset from the start of an array object's memory to
     * the memory used to store its initial (zeroeth) element.
     *
     * @param clazz non-null; class in question; must be an array class
     * @return the offset to the initial element
     */
    public int arrayBaseOffset(Class<?> clazz) {
        return unsafe.arrayBaseOffset(clazz);
    }

    /**
     * Gets the size of each element of the given array class.
     *
     * @param clazz non-null; class in question; must be an array class
     * @return &gt; 0; the size of each element of the array
     */
    public int arrayIndexScale(Class<?> clazz) {
        return unsafe.arrayIndexScale(clazz);
    }

    /**
     * Performs a compare-and-set operation on an <code>int</code>
     * field within the given object.
     *
     * @param obj           non-null; object containing the field
     * @param offset        offset to the field within <code>obj</code>
     * @param expectedValue expected value of the field
     * @param newValue      new value to store in the field if the contents are
     *                      as expected
     * @return <code>true</code> if the new value was in fact stored, and
     * <code>false</code> if not
     */
    public boolean compareAndSwapInt(Object obj, long offset, int expectedValue, int newValue) {
        return unsafe.compareAndSwapInt(obj, offset, expectedValue, newValue);
    }

    /**
     * Performs a compare-and-set operation on a <code>long</code>
     * field within the given object.
     *
     * @param obj           non-null; object containing the field
     * @param offset        offset to the field within <code>obj</code>
     * @param expectedValue expected value of the field
     * @param newValue      new value to store in the field if the contents are
     *                      as expected
     * @return <code>true</code> if the new value was in fact stored, and
     * <code>false</code> if not
     */
    public boolean compareAndSwapLong(Object obj, long offset, long expectedValue, long newValue) {
        return unsafe.compareAndSwapLong(obj, offset, expectedValue, newValue);
    }

    /**
     * Performs a compare-and-set operation on an <code>Object</code>
     * field (that is, a reference field) within the given object.
     *
     * @param obj           non-null; object containing the field
     * @param offset        offset to the field within <code>obj</code>
     * @param expectedValue expected value of the field
     * @param newValue      new value to store in the field if the contents are
     *                      as expected
     * @return <code>true</code> if the new value was in fact stored, and
     * <code>false</code> if not
     */
    public boolean compareAndSwapObject(Object obj, long offset, Object expectedValue, Object newValue) {
        return unsafe.compareAndSwapObject(obj, offset, expectedValue, newValue);
    }

    /**
     * Gets an <code>int</code> field from the given object.
     *
     * @param obj    non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public int getInt(Object obj, long offset) {
        return unsafe.getInt(obj, offset);
    }

    /**
     * Gets an <code>int</code> field from the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj    non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public int getIntVolatile(Object obj, long offset) {
        return unsafe.getIntVolatile(obj, offset);
    }

    /**
     * Gets a <code>long</code> field from the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj    non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public long getLong(Object obj, long offset) {
        return unsafe.getLong(obj, offset);
    }

    /**
     * Gets a <code>long</code> field from the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj    non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public long getLongVolatile(Object obj, long offset) {
        return unsafe.getLongVolatile(obj, offset);
    }

    /**
     * Gets an <code>Object</code> field from the given object.
     *
     * @param obj    non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public Object getObject(Object obj, long offset) {
        return unsafe.getObject(obj, offset);
    }

    /**
     * Gets an <code>Object</code> field from the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj    non-null; object containing the field
     * @param offset offset to the field within <code>obj</code>
     * @return the retrieved value
     */
    public Object getObjectVolatile(Object obj, long offset) {
        return unsafe.getObjectVolatile(obj, offset);
    }

    /**
     * Gets the raw byte offset from the start of an object's memory to
     * the memory used to store the indicated instance field.
     *
     * @param field non-null; the field in question, which must be an
     *              instance field
     * @return the offset to the field
     */
    public long objectFieldOffset(Field field) {
        return unsafe.objectFieldOffset(field);
    }

    /**
     * Parks the calling thread for the specified amount of time,
     * unless the "permit" for the thread is already available (due to
     * a previous call to {@link #unpark}. This method may also return
     * spuriously (that is, without the thread being told to unpark
     * and without the indicated amount of time elapsing).
     * <p>
     * <p>See {@link java.util.concurrent.locks.LockSupport} for more
     * in-depth information of the behavior of this method.</p>
     *
     * @param absolute whether the given time value is absolute
     *                 milliseconds-since-the-epoch (<code>true</code>) or relative
     *                 nanoseconds-from-now (<code>false</code>)
     * @param time     the (absolute millis or relative nanos) time value
     */
    public void park(boolean absolute, long time) {
        unsafe.park(absolute, time);
    }

    /**
     * Unparks the given object, which must be a {@link Thread}.
     * <p>
     * <p>See {@link java.util.concurrent.locks.LockSupport} for more
     * in-depth information of the behavior of this method.</p>
     *
     * @param obj non-null; the object to unpark
     */
    public void unpark(Object obj) {
        unsafe.unpark(obj);
    }

    /**
     * Stores an <code>int</code> field into the given object.
     *
     * @param obj      non-null; object containing the field
     * @param offset   offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putInt(Object obj, long offset, int newValue) {
        unsafe.putInt(obj, offset, newValue);
    }

    /**
     * Stores an <code>int</code> field into the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj      non-null; object containing the field
     * @param offset   offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putIntVolatile(Object obj, long offset, int newValue) {
        unsafe.putIntVolatile(obj, offset, newValue);
    }

    /**
     * Stores a <code>long</code> field into the given object.
     *
     * @param obj      non-null; object containing the field
     * @param offset   offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putLong(Object obj, long offset, long newValue) {
        unsafe.putLong(obj, offset, newValue);
    }

    /**
     * Stores a <code>long</code> field into the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj      non-null; object containing the field
     * @param offset   offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putLongVolatile(Object obj, long offset, long newValue) {
        unsafe.putLongVolatile(obj, offset, newValue);
    }

    /**
     * Stores an <code>Object</code> field into the given object.
     *
     * @param obj      non-null; object containing the field
     * @param offset   offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putObject(Object obj, long offset, Object newValue) {
        unsafe.putObject(obj, offset, newValue);
    }

    /**
     * Stores an <code>Object</code> field into the given object,
     * using <code>volatile</code> semantics.
     *
     * @param obj      non-null; object containing the field
     * @param offset   offset to the field within <code>obj</code>
     * @param newValue the value to store
     */
    public void putObjectVolatile(Object obj, long offset, Object newValue) {
        unsafe.putObjectVolatile(obj, offset, newValue);
    }

    /**
     * Lazy set an int field.
     */
    public void putOrderedInt(Object obj, long offset, int newValue) {
        unsafe.putOrderedInt(obj, offset, newValue);
    }

    /**
     * Lazy set a long field.
     */
    public void putOrderedLong(Object obj, long offset, long newValue) {
        unsafe.putOrderedLong(obj, offset, newValue);
    }

    /**
     * Lazy set an object field.
     */
    public void putOrderedObject(Object obj, long offset, Object newValue) {
        unsafe.putOrderedObject(obj, offset, newValue);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public int addressSize() {
        return unsafe.addressSize();
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public int pageSize() {
        return unsafe.pageSize();
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public long allocateMemory(long bytes) {
        return unsafe.allocateMemory(bytes);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void freeMemory(long address) {
        unsafe.freeMemory(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void setMemory(long address, long bytes, byte value) {
        unsafe.setMemory(address, bytes, value);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public byte getByte(long address) {
        return unsafe.getByte(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void putByte(Object obj, long offset, byte newValue) {
        unsafe.putByte(obj, offset, newValue);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public short getShort(long address) {
        return unsafe.getShort(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void putShort(Object obj, long offset, short newValue) {
        unsafe.putShort(obj, offset, newValue);
    }

    /**
     * @since 1.8
     */
    public char getChar(long address) {
        return unsafe.getChar(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void putChar(Object obj, long offset, char newValue) {
        unsafe.putChar(obj, offset, newValue);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public int getInt(long address) {
        return unsafe.getInt(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public long getLong(long address) {
        return unsafe.getLong(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public float getFloat(long address) {
        return unsafe.getFloat(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void putFloat(Object obj, long offset, float newValue) {
        unsafe.putFloat(obj, offset, newValue);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public double getDouble(long address) {
        return unsafe.getDouble(address);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void putDouble(Object obj, long offset, double newValue) {
        unsafe.putDouble(obj, offset, newValue);
    }

    /**
     * @since 1.8
     */
    @TargetApi(24)
    public void copyMemory(long srcAddr, long dstAddr, long bytes) {
        unsafe.copyMemory(srcAddr, dstAddr, bytes);
    }

    // The following contain CAS-based Java implementations used on
    // platforms not supporting native instructions

    /**
     * Atomically adds the given value to the current value of a field
     * or array element within the given object {@code o}
     * at the given {@code offset}.
     *
     * @param o      object/array to update the field/element in
     * @param offset field/element offset
     * @param delta  the value to add
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public int getAndAddInt(Object o, long offset, int delta) {
        return unsafe.getAndAddInt(o, offset, delta);
    }

    /**
     * Atomically adds the given value to the current value of a field
     * or array element within the given object {@code o}
     * at the given {@code offset}.
     *
     * @param o      object/array to update the field/element in
     * @param offset field/element offset
     * @param delta  the value to add
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public long getAndAddLong(Object o, long offset, long delta) {
        return unsafe.getAndAddLong(o, offset, delta);
    }

    /**
     * Atomically exchanges the given value with the current value of
     * a field or array element within the given object {@code o}
     * at the given {@code offset}.
     *
     * @param o        object/array to update the field/element in
     * @param offset   field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public int getAndSetInt(Object o, long offset, int newValue) {
        return unsafe.getAndSetInt(o, offset, newValue);
    }

    /**
     * Atomically exchanges the given value with the current value of
     * a field or array element within the given object {@code o}
     * at the given {@code offset}.
     *
     * @param o        object/array to update the field/element in
     * @param offset   field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public long getAndSetLong(Object o, long offset, long newValue) {
        return unsafe.getAndSetLong(o, offset, newValue);
    }

    /**
     * Atomically exchanges the given reference value with the current
     * reference value of a field or array element within the given
     * object {@code o} at the given {@code offset}.
     *
     * @param o        object/array to update the field/element in
     * @param offset   field/element offset
     * @param newValue new value
     * @return the previous value
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public Object getAndSetObject(Object o, long offset, Object newValue) {
        return unsafe.getAndSetObject(o, offset, newValue);
    }

    /**
     * Ensures that loads before the fence will not be reordered with loads and
     * stores after the fence; a "LoadLoad plus LoadStore barrier".
     * <p>
     * Corresponds to C11 atomic_thread_fence(memory_order_acquire)
     * (an "acquire fence").
     * <p>
     * A pure LoadLoad fence is not provided, since the addition of LoadStore
     * is almost always desired, and most current hardware instructions that
     * provide a LoadLoad barrier also provide a LoadStore barrier for free.
     *
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public void loadFence() {
        unsafe.loadFence();
    }

    /**
     * Ensures that loads and stores before the fence will not be reordered with
     * stores after the fence; a "StoreStore plus LoadStore barrier".
     * <p>
     * Corresponds to C11 atomic_thread_fence(memory_order_release)
     * (a "release fence").
     * <p>
     * A pure StoreStore fence is not provided, since the addition of LoadStore
     * is almost always desired, and most current hardware instructions that
     * provide a StoreStore barrier also provide a LoadStore barrier for free.
     *
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public void storeFence() {
        unsafe.storeFence();
    }

    /**
     * Ensures that loads and stores before the fence will not be reordered
     * with loads and stores after the fence.  Implies the effects of both
     * loadFence() and storeFence(), and in addition, the effect of a StoreLoad
     * barrier.
     * <p>
     * Corresponds to C11 atomic_thread_fence(memory_order_seq_cst).
     *
     * @since 1.8
     */
    // @HotSpotIntrinsicCandidate
    @TargetApi(24)
    public void fullFence() {
        unsafe.fullFence();
    }
}
