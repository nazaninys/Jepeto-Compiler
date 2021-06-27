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
		getstatic java/lang/System/out Ljava/io/PrintStream;
		new Fptr
		dup
		aload 0
		ldc "g"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 1
		aload 1
		ldc 2
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 1
		ldc 1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 1
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		ldc 8
		iadd
		invokevirtual java/io/PrintStream/println(I)V
		return
.end method
.method public f(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;
.limit stack 128
.limit locals 128
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		imul
		iadd
		invokevirtual java/io/PrintStream/println(I)V
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc 1
		invokevirtual java/io/PrintStream/println(Z)V
		getstatic java/lang/System/out Ljava/io/PrintStream;
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 4
		aload 4
		ldc 10
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 4
		ldc 11
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 4
		ldc 12
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 4
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		invokevirtual List/getSize()I
		invokevirtual java/io/PrintStream/println(I)V
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "["
		invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
		ldc 0
		istore 7
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 5
		aload 5
		ldc 1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 5
		ldc 2
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 5
		ldc 3
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 5
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		dup
		ldc 4
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual List/addElement(Ljava/lang/Object;)V
		invokevirtual List/getSize()I
		istore 6
	Label_0:
		iload 7
		iload 6
		if_icmpge Label_1
		getstatic java/lang/System/out Ljava/io/PrintStream;
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 5
		aload 5
		ldc 1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 5
		ldc 2
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 5
		ldc 3
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 5
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		dup
		ldc 4
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual List/addElement(Ljava/lang/Object;)V
		iload 7
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual java/io/PrintStream/print(I)V
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc ","
		invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
		ldc 1
		iload 7
		iadd
		istore 7
		goto Label_0
	Label_1:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "]"
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		if_icmple Label_4
		ldc 1
		goto Label_5
		Label_4:
		ldc 0
		Label_5:
		ifeq Label_2
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "Hello"
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
		goto Label_3
	Label_2:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "Bye"
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	Label_3:
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 8
		aload 8
		ldc 1
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 8
		ldc 2
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 8
		ldc 3
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 8
		ldc 4
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 8
		ldc 10
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 8
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		ldc 3
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		areturn
.end method
.method public g(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;
.limit stack 128
.limit locals 128
		getstatic java/lang/System/out Ljava/io/PrintStream;
		new Fptr
		dup
		aload 0
		ldc "f"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 3
		aload 3
		aload 1
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 3
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 3
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		ldc 10
		if_icmpne Label_6
		ldc 1
		goto Label_7
		Label_6:
		ldc 0
		Label_7:
		invokevirtual java/io/PrintStream/println(Z)V
		ldc 100
		ldc 2
		idiv
		ldc 7
		ldc 10
		imul
		iadd
		ldc 8
		isub
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		areturn
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "unreachable"
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.end method
