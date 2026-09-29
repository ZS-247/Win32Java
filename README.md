## Win32Java
My attempts to call native win32 api functions with the Java Foreign Function and Memory (FFM) API.

The example given at [docs.oracle.com](https://docs.oracle.com/en/java/javase/22/core/calling-c-library-function-foreign-function-and-memory-api.html#GUID-E7255CE9-5A95-437C-B37A-276B6C9B5F4D) covers calling the c stdlib function strlen but I wanted to called the [MessgeBoxA](
https://learn.microsoft.com/en-us/windows/win32/api/winuser/nf-winuser-messageboxa) function from user32.dll

The MessageboxA functions signature is as follows:
```c
int MessageBoxA(HWND hWnd, LPCSTR lpText, LPCSTR lpCaption, UINT uType);
```
And and are called from Java with a simple wrapper. For the sake of convenience, uType is always set to 0 resulting in default messagebox behavior.  
```Java
public static void main(String[] args) {
        try {
            int ret = CallMessageboxA("hello", "world");

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
}
```
