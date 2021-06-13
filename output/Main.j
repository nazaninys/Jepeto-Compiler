.class Main
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 128
.limit locals 128
		new Main
		invokespecial Main/<init>()V
		return
.end method
.method public <init>()V
.limit stack 128
.limit locals 128
		aload 0
		invokespecial java/lang/Object/<init>()V
		new Fptr
		dup
		aload 0
		ldc "f"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 1
		aload 1
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		return
.end method
.method public f()V
.limit stack 128
.limit locals 128
		ldc 0
		invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;
		invokevirtual java/lang/Boolean/booleanValue()Z
		ifeq Label_0
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "hello"
		
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
		goto Label_1
	Label_0:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "bye"
		
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	Label_1:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "hi"
		
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
		return
.end method
