// Generated from D:/University/TA_Lessons/Compiler-1400-Spring/Jepeto-Phase1/src/main\Jepeto.g4 by ANTLR 4.8
package main;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link JepetoVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class JepetoBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements JepetoVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitProgram(JepetoParser.ProgramContext ctx) { return visitChildren(ctx); }
}