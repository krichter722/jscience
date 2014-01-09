/*
 * JScience - Java(TM) Tools and Libraries for the Advancement of Sciences.
 * Copyright (C) 2014 - JScience (http://jscience.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package org.jscience.mathematics.linear;

import java.io.IOException;
import java.util.List;

import javolution.text.Cursor;
import javolution.text.DefaultTextFormat;
import javolution.text.TextFormat;
import javolution.util.FastCollection;
import javolution.util.FastTable;
import javolution.util.Index;
import javolution.xml.DefaultXMLFormat;
import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.jscience.mathematics.structure.Field;

/**
 * <p> A {@link Vector vector} having a larger number of elements
 *     different from zero.</p>
 *         
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, January 26, 2014
 * @see SparseVector
 */
@DefaultTextFormat(DenseVector.Text.class)
@DefaultXMLFormat(DenseVector.XML.class)
public interface DenseVector<F extends Field<F>> extends Vector<F> {

	/**
	 * Defines the default text format for dense vectors (list of elements). 
	 */
	public static class Text extends TextFormat<DenseVector<?>> {

		@Override
		public Appendable format(DenseVector<?> that, final Appendable dest)
				throws IOException {
			TextFormat<FastCollection<?>> format = new FastCollection.Text();
			return format.format(that.getData(), dest);
		}

		@Override
		public DenseVector<?> parse(CharSequence csq, Cursor cursor)
				throws IllegalArgumentException {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * Defines the default XML representation for dense vectors. For example:
	 * [code]
	 * <DenseVector dimension="2">
	 *     <Rational value="1/3" />
	 *     <Rational value="3/5" />
	 * </DenseVector>[/code]
	 */
	public static class XML extends XMLFormat<DenseVector<?>> {

		@Override
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public DenseVector<?> newInstance(Class<? extends DenseVector<?>> cls,
				InputElement xml) throws XMLStreamException {
			Field[] elements = (Field[]) new Object[xml.getAttribute(
					"dimension", 0)];
			for (int i = 0, n = elements.length; i < n; i++) {
				elements[i] = xml.getNext();
			}
			return Vectors.denseVector(elements);
		}

		@Override
		public void read(InputElement xml, DenseVector<?> v)
				throws XMLStreamException {
			// Do nothing, vector already read.
		}

		@Override
		public void write(DenseVector<?> v, OutputElement xml)
				throws XMLStreamException {
			xml.setAttribute("dimension", v.getDimension());
			for (Object obj : v.getData()) {
				xml.add(obj);
			}
		}
	}

	@Override
	DenseMatrix<F> asColumn();

	@Override
	DenseMatrix<F> asRow();

	@Override
	DenseVector<F> cross(Vector<F> that);

	/**
	 * Returns this vector's data elements (unmodifiable).
	 */
	FastTable<F> getData();

	@Override
	DenseVector<F> getSubVector(List<Index> indices);

	@Override
	DenseVector<F> minus(Vector<F> that);

	@Override
	DenseVector<F> opposite();

	@Override
	DenseVector<F> plus(Vector<F> that);

	@Override
	DenseMatrix<F> tensor(Vector<F> that);

	@Override
	DenseVector<F> times(F k);

}
