/**
 * Giro.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consultaIUEwsdl_pkg;

public class Giro  implements java.io.Serializable {
    private java.lang.String fecha;

    private java.lang.String tipo;

    private java.lang.String decreto;

    private java.lang.String vencimiento;

    private java.lang.String sede;

    public Giro() {
    }

    public Giro(
           java.lang.String fecha,
           java.lang.String tipo,
           java.lang.String decreto,
           java.lang.String vencimiento,
           java.lang.String sede) {
           this.fecha = fecha;
           this.tipo = tipo;
           this.decreto = decreto;
           this.vencimiento = vencimiento;
           this.sede = sede;
    }


    /**
     * Gets the fecha value for this Giro.
     * 
     * @return fecha
     */
    public java.lang.String getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this Giro.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.String fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the tipo value for this Giro.
     * 
     * @return tipo
     */
    public java.lang.String getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this Giro.
     * 
     * @param tipo
     */
    public void setTipo(java.lang.String tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the decreto value for this Giro.
     * 
     * @return decreto
     */
    public java.lang.String getDecreto() {
        return decreto;
    }


    /**
     * Sets the decreto value for this Giro.
     * 
     * @param decreto
     */
    public void setDecreto(java.lang.String decreto) {
        this.decreto = decreto;
    }


    /**
     * Gets the vencimiento value for this Giro.
     * 
     * @return vencimiento
     */
    public java.lang.String getVencimiento() {
        return vencimiento;
    }


    /**
     * Sets the vencimiento value for this Giro.
     * 
     * @param vencimiento
     */
    public void setVencimiento(java.lang.String vencimiento) {
        this.vencimiento = vencimiento;
    }


    /**
     * Gets the sede value for this Giro.
     * 
     * @return sede
     */
    public java.lang.String getSede() {
        return sede;
    }


    /**
     * Sets the sede value for this Giro.
     * 
     * @param sede
     */
    public void setSede(java.lang.String sede) {
        this.sede = sede;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Giro)) return false;
        Giro other = (Giro) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.decreto==null && other.getDecreto()==null) || 
             (this.decreto!=null &&
              this.decreto.equals(other.getDecreto()))) &&
            ((this.vencimiento==null && other.getVencimiento()==null) || 
             (this.vencimiento!=null &&
              this.vencimiento.equals(other.getVencimiento()))) &&
            ((this.sede==null && other.getSede()==null) || 
             (this.sede!=null &&
              this.sede.equals(other.getSede())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getDecreto() != null) {
            _hashCode += getDecreto().hashCode();
        }
        if (getVencimiento() != null) {
            _hashCode += getVencimiento().hashCode();
        }
        if (getSede() != null) {
            _hashCode += getSede().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Giro.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:consultaIUEwsdl", "giro"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decreto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "decreto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vencimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "vencimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sede");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sede"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
