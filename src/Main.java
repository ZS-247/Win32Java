import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class Main {
    public static int CallMessageboxA(String text, String title) throws Throwable {

        try(Arena arena = Arena.ofConfined()){
            MemorySegment nativeText = arena.allocateFrom(text);
            MemorySegment nativeTitle = arena.allocateFrom(title);


            Linker linker = Linker.nativeLinker();
            SymbolLookup user32 = SymbolLookup.libraryLookup("user32.dll", arena);
            MemorySegment MessageBoxA_addr = user32.find("MessageBoxA").orElseThrow();

            FunctionDescriptor MessageBoxA_sig =
                    FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.ADDRESS,ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.JAVA_INT);
            MethodHandle MessageBoxA = linker.downcallHandle(MessageBoxA_addr,MessageBoxA_sig);

            return (int)MessageBoxA.invokeExact(MemorySegment.NULL, nativeText, nativeTitle,0);
        }

    }
    public static void main(String[] args) {
        try {
            int ret = CallMessageboxA("hello", "world");

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

}