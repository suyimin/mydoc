/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /data/program/worksapce/ServiceTestDemo_AIDLServer/src/com/seven/aidlserver/ICountService.aidl
 */
package com.seven.aidlserver;
public interface ICountService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.seven.aidlserver.ICountService
{
private static final java.lang.String DESCRIPTOR = "com.seven.aidlserver.ICountService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.seven.aidlserver.ICountService interface,
 * generating a proxy if needed.
 */
public static com.seven.aidlserver.ICountService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.seven.aidlserver.ICountService))) {
return ((com.seven.aidlserver.ICountService)iin);
}
return new com.seven.aidlserver.ICountService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getCount:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getCount();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.seven.aidlserver.ICountService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public int getCount() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCount, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getCount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public int getCount() throws android.os.RemoteException;
}
