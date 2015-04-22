package com.bryanreinero.hum.element;

import java.util.ArrayList;
import java.util.List;

import com.bryanreinero.hum.visitor.*;

public class Compare extends HumElement implements Visitable {
    
    private List<Visitable> children = new ArrayList<Visitable>();
    private Operator op;
    
    public enum Operator {
    	EX("exists"),
        EQ("eq"),
        GE("ge"),
        LE("le"),
        LT("lt"),
        GT("gt"),
        NE("ne"),
        SEQ("seq"),
        ISEQ("iseq"),
        INSEQ("inseq"),
        CONTAINS("contains"),
        STARTS_WITH("starts-with"),
        ENDS_WITH("ends-with"),
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
    
    public List<Visitable> getChildren() {
        return children;
    }

    public void addChild(Value element){
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