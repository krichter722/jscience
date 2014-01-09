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
import javolution.util.FastMap;
import javolution.util.FastTable;
import javolution.util.Index;
import javolution.xml.DefaultXMLFormat;
import javolution.xml.XMLFormat;
import javolution.xml.stream.XMLStreamException;

import org.jscience.mathematics.structure.Field;

/**
 * <p> A {@link Vector vector} having a larger number of elements set to 
 *     zero.</p>
 *
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle</a>
 * @version 5.0, December 12, 2009
 * @see DenseVector
 */
@DefaultTextFormat(SparseVector.Text.class)
@DefaultXMLFormat(SparseVector.XML.class)
public interface SparseVector<F extends Field<F>> extends Vector<F> {
	
	/**
	 * Defines the default text format for sparse vectors (mapping index-elements). 
	 */
	public static class Text extends TextFormat<SparseVector<?>> {

		@Override
		public Appendable format(SparseVector<?> that, final Appendable dest)
				throws IOException {
			TextFormat<FastCollection<?>> fmt = new FastCollection.Text();
			FastMap<Index, Object> mapping = new FastMap<Index, Object>();
			FastTable<?> data = that.getData();
			int j = 0;
			for (Index i : that.getIndices()) {
				mapping.put(i, data.get(j++));
			}
			return fmt.format(mapping.entrySet(), dest);
		}

		@Override
		public SparseVector<?> parse(CharSequence csq, Cursor cursor)
				throws IllegalArgumentException {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * Defines the default XML representation for sparse vectors. For example:
	 * [code]
	 * <SparseVector dimension="4">
     *     <Zero class="org.jscience.mathematics.number.Rational" value="0"/>
	 *     <Index value="1"/>
     *     <Value class="org.jscience.mathematics.number.Rational" value="1/3"/>
	 *     <Index value="3"/>
     *     <Value class="org.jscience.mathematics.number.Rational" value="5/3"/>
	 * </SparseVector>[/code]
	 */
	public static class XML extends XMLFormat<SparseVector<?>> {

		@Override
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public SparseVector<?> newInstance(Class<? extends SparseVector<?>> cls,
				InputElement xml) throws XMLStreamException {
			 int dimension = xml.getAttribute("dimension", 0);
			 Field zero = (Field) xml.get("Zero");
		     FastTable<Index> indices = new FastTable<Index>();
		     FastTable<Field> elements = new FastTable<Field>();
		     while (xml.hasNext()) {
	                indices.add(xml.get("Index", Index.class));
	                elements.add((Field) xml.get("Value"));
	        }
			return Vectors.sparseVector(dimension, zero, indices,
					elements.toArray(new Field[elements.size()]));
	   }

		@Override
		public void read(InputElement xml, SparseVector<?> v)
				throws XMLStreamException {
			// Do nothing (already created).
		}

		@Override
		public void write(SparseVector<?> v, OutputElement xml)
				throws XMLStreamException {
			xml.setAttribute("dimension", v.getDimension());
			xml.add(v.getZero(), "Zero");
			List<Index> indices = v.getIndices();
			FastTable<?> data = v.getData();
			for (Index i : indices) {
				xml.add(i, "Index", Index.class);
				xml.add(data.get(i.intValue()), "Value");							
			}
	   }

	}
	
	/**
	 * Returns this sparse vector's non-zero data elements (unmodifiable).
	 */
	FastTable<F> getData();

	/**
	 * Returns the indices of non-zero data elements (ordered).
	 */
	List<Index> getIndices();

	/**
	 * Returns the value of the zero elements of this sparse vector.
	 */
    F getZero();
    
    @Override
	SparseVector<F> cross(Vector<F> that);

	@Override
	SparseMatrix<F> asColumn();

	@Override
	SparseMatrix<F> asRow();

	@Override
	SparseVector<F> getSubVector(List<Index> indices);

	@Override
	SparseVector<F> minus(Vector<F> that);

	@Override
	SparseVector<F> opposite();

	@Override
	SparseVector<F> plus(Vector<F> that);

	@Override
	SparseMatrix<F> tensor(Vector<F> that);

	@Override
	SparseVector<F> times(F k);

}
