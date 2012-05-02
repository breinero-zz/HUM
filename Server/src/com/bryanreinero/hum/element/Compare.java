package com.bryanreinero.hum.element;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlEnumValue;

import com.bryanreinero.hum.visitor.*;

public class Compare extends HumElement {
    
    private ArrayList<HumElement> children;
    private Operator op;
    
    public enum Operator {

        @XmlEnumValue("eq")
        EQ("eq"),
        @XmlEnumValue("ge")
        GE("ge"),
        @XmlEnumValue("le")
        LE("le"),
        @XmlEnumValue("lt")
        LT("lt"),
        @XmlEnumValue("gt")
        GT("gt"),
        @XmlEnumValue("ne")
        NE("ne"),
        @XmlEnumValue("seq")
        SEQ("seq"),
        @XmlEnumValue("iseq")
        ISEQ("iseq"),
        @XmlEnumValue("inseq")
        INSEQ("inseq"),
        @XmlEnumValue("contains")
        CONTAINS("contains"),
        @XmlEnumValue("starts-with")
        STARTS_WITH("starts-with"),
        @XmlEnumValue("ends-with")
        ENDS_WITH("ends-with"),
        @XmlEnumValue("any")
        ANY("any");
        private final String value;

        Operator(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static Operator fromValue(String v) {
            for (Operator c: Operator.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

    }
    
    /**
     * Gets the value of the op property.
     * 
     * @return
     *     possible object is
     *     {@link Operator }
     *     
     */
    public Operator getOp() {
        return op;
    }

    /**
     * Sets the value of the op property.
     * 
     * @param value
     *     allowed object is
     *     {@link Operator }
     *     
     */
    public void setOp(Operator value) {
        this.op = value;
    }
    
    public ArrayList<HumElement> getChildren() {
        return children;
    }

    @Override
    public void addChild(Value element){
    	if(children == null)
    		children = new ArrayList<HumElement>();
    	
    	children.add(element);
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}