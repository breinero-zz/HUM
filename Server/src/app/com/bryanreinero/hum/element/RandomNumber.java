package com.bryanreinero.hum.element;

import javax.xml.bind.annotation.XmlAttribute;

import com.bryanreinero.hum.visitor.Visitor;

public class RandomNumber extends HumElement {

    @XmlAttribute(name = "max")
    protected Integer max;

    /**
     * Gets the value of the max property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMax() {
        return max;
    }

    /**
     * Sets the value of the max property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMax(Integer value) {
        this.max = value;
    }

    @Override
    public void accept(Visitor aVisitor) {
        aVisitor.visit(this);
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}

}
