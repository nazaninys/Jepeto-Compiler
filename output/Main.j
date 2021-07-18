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
		ldc "start"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 1
		aload 1
		ldc 1
		invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 1
		ldc 20
		ldc 6
		ldc 4
		imul
		iadd
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 1
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		return
.end method
.method public start(Ljava/lang/Boolean;Ljava/lang/Integer;)V
.limit stack 128
.limit locals 128
		aload 1
		invokevirtual java/lang/Boolean/booleanValue()Z
		ifne Label_4
		ldc 1
		goto Label_5
		Label_4:
		ldc 0
		Label_5:
		ifne Label_2
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		ldc 100
		if_icmple Label_6
		ldc 1
		goto Label_7
		Label_6:
		ldc 0
		Label_7:
		ifne Label_2
		ldc 0
		goto Label_3
		Label_2:
		ldc 1
		Label_3:
		ifeq Label_0
		aload 1
		invokevirtual java/lang/Boolean/booleanValue()Z
		ifeq Label_8
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		ldc 30
		idiv
		invokevirtual java/io/PrintStream/println(I)V
		goto Label_9
	Label_8:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		ldc 30
		imul
		invokevirtual java/io/PrintStream/println(I)V
	Label_9:
		goto Label_1
	Label_0:
	Label_1:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		new Fptr
		dup
		aload 0
		ldc "anms"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 8
		aload 8
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		checkcast Fptr
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 7
		aload 7
		ldc "hi"
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 7
		ldc 0
		invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 7
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		checkcast java/lang/Boolean
		invokevirtual java/lang/Boolean/booleanValue()Z
		invokevirtual java/io/PrintStream/println(Z)V
		return
.end method
.method public anms()LFptr;
.limit stack 128
.limit locals 128
		new Fptr
		dup
		aload 0
		ldc "printList"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 1
		aload 1
		new List
		dup
		new List
		dup
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 2
		aload 2
		ldc 11
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 2
		ldc 13
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 2
		ldc 17
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 2
		ldc 19
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 2
		invokespecial List/<init>(Ljava/util/ArrayList;)V
		invokespecial List/<init>(LList;)V
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 1
		ldc 0
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 1
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		new Fptr
		dup
		aload 0
		ldc "anonymous1"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		
		areturn
.end method
.method public printList(LList;Ljava/lang/Integer;)V
.limit stack 128
.limit locals 128
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		ldc 4
		if_icmpne Label_20
		ldc 1
		goto Label_21
		Label_20:
		ldc 0
		Label_21:
		ifeq Label_18
		return
		goto Label_19
	Label_18:
	Label_19:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 1
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual List/getElement(I)Ljava/lang/Object;
		checkcast java/lang/Integer
		invokevirtual java/lang/Integer/intValue()I
		invokevirtual java/io/PrintStream/println(I)V
		new Fptr
		dup
		aload 0
		ldc "printList"
		invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V
		new java/util/ArrayList
		dup
		invokespecial java/util/ArrayList/<init>()V
		astore 4
		aload 4
		new List
		dup
		aload 1
		invokespecial List/<init>(LList;)V
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 4
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		ldc 1
		iadd
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z
		pop
		aload 4
		invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;
		pop
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "["
		invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
		ldc 0
		istore 7
		aload 1
		dup
		aload 2
		invokevirtual java/lang/Integer/intValue()I
		invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;
		invokevirtual List/addElement(Ljava/lang/Object;)V
		astore 5
		aload 5
		invokevirtual List/getSize()I
		istore 6
	Label_22:
		iload 7
		iload 6
		if_icmpge Label_23
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 5
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
		goto Label_22
	Label_23:
		iload 6
		ifeq Label_24
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc ""
		invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
	Label_24:
		getstatic java/lang/System/out Ljava/io/PrintStream;
		ldc "]"
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
		return
.end method
.method public anonymous1(Ljava/lang/String;Ljava/lang/Boolean;)Ljava/lang/Boolean;
		.limit stack 128
		.limit locals 128
		getstatic java/lang/System/out Ljava/io/PrintStream;
		aload 1
		invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
		ldc 1
		ifne Label_10
		ldc 0
		ifeq Label_12
		ldc 1
		ifne Label_14
		ldc 1
		goto Label_15
		Label_14:
		ldc 0
		Label_15:
		aload 2
		invokevirtual java/lang/Boolean/booleanValue()Z
		if_icmpne Label_16
		ldc 1
		goto Label_17
		Label_16:
		ldc 0
		Label_17:
		ifeq Label_12
		ldc 1
		goto Label_13
		Label_12:
		ldc 0
		Label_13:
		ifne Label_10
		ldc 0
		goto Label_11
		Label_10:
		ldc 1
		Label_11:
		invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;
		areturn
		.end method
