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
		checkcast Fptr
		pop
		return
.end method
.method public f()LFptr;
.limit stack 128
.limit locals 128
		new Fptr
		dup
		aload 0
		ldc "anonymous1"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		
		areturn
.end method
