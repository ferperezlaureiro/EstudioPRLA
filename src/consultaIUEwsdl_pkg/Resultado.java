/**
 * Resultado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package consultaIUEwsdl_pkg;

public class Resultado  implements java.io.Serializable {
    private java.lang.String estado;

    private java.lang.String origen;

    private java.lang.String expediente;

    private java.lang.String caratula;

    private java.lang.String abogado_actor;

    private java.lang.String abogado_demandante;

    private consultaIUEwsdl_pkg.Giro[] movimientos;

    public Resultado() {
    }

    public Resultado(
           java.lang.String estado,
           java.lang.String origen,
           java.lang.String expediente,
           java.lang.String caratula,
           java.lang.String abogado_actor,
           java.lang.String abogado_demandante,
           consultaIUEwsdl_pkg.Giro[] movimientos) {
           this.estado = estado;
           this.origen = origen;
           this.expediente = expediente;
           this.caratula = caratula;
           this.abogado_actor = abogado_actor;
           this.abogado_demandante = abogado_demandante;
           this.movimientos = movimientos;
    }


    /**
     * Gets the estado value for this Resultado.
     * 
     * @return estado
     */
    public java.lang.String getEstado() {
        return estado;
    }


    /**
     * Sets the estado value for this Resultado.
     * 
     * @param estado
     */
    public void setEstado(java.lang.String estado) {
        this.estado = estado;
    }


    /**
     * Gets the origen value for this Resultado.
     * 
     * @return origen
     */
    public java.lang.String getOrigen() {
        return origen;
    }


    /**
     * Sets the origen value for this Resultado.
     * 
     * @param origen
     */
    public void setOrigen(java.lang.String origen) {
        this.origen = origen;
    }


    /**
     * Gets the expediente value for this Resultado.
     * 
     * @return expediente
     */
    public java.lang.String getExpediente() {
        return expediente;
    }


    /**
     * Sets the expediente value for this Resultado.
     * 
     * @param expediente
     */
    public void setExpediente(java.lang.String expediente) {
        this.expediente = expediente;
    }


    /**
     * Gets the caratula value for this Resultado.
     * 
     * @return caratula
     */
    public java.lang.String getCaratula() {
        return caratula;
    }


    /**
     * Sets the caratula value for this Resultado.
     * 
     * @param caratula
     */
    public void setCaratula(java.lang.String caratula) {
        this.caratula = caratula;
    }


    /**
     * Gets the abogado_actor value for this Resultado.
     * 
     * @return abogado_actor
     */
    public java.lang.String getAbogado_actor() {
        return abogado_actor;
    }


    /**
     * Sets the abogado_actor value for this Resultado.
     * 
     * @param abogado_actor
     */
    public void setAbogado_actor(java.lang.String abogado_actor) {
        this.abogado_actor = abogado_actor;
    }


    /**
     * Gets the abogado_demandante value for this Resultado.
     * 
     * @return abogado_demandante
     */
    public java.lang.String getAbogado_demandante() {
        return abogado_demandante;
    }


    /**
     * Sets the abogado_demandante value for this Resultado.
     * 
     * @param abogado_demandante
     */
    public void setAbogado_demandante(java.lang.String abogado_demandante) {
        this.abogado_demandante = abogado_demandante;
    }


    /**
     * Gets the movimientos value for this Resultado.
     * 
     * @return movimientos
     */
    public consultaIUEwsdl_pkg.Giro[] getMovimientos() {
        return movimientos;
    }


    /**
     * Sets the movimientos value for this Resultado.
     * 
     * @param movimientos
     */
    public void setMovimientos(consultaIUEwsdl_pkg.Giro[] movimientos) {
        this.movimientos = movimientos;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Resultado)) return false;
        Resultado other = (Resultado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estado==null && other.getEstado()==null) || 
             (this.estado!=null &&
              this.estado.equals(other.getEstado()))) &&
            ((this.origen==null && other.getOrigen()==null) || 
             (this.origen!=null &&
              this.origen.equals(other.getOrigen()))) &&
            ((this.expediente==null && other.getExpediente()==null) || 
             (this.expediente!=null &&
              this.expediente.equals(other.getExpediente()))) &&
            ((this.caratula==null && other.getCaratula()==null) || 
             (this.caratula!=null &&
              this.caratula.equals(other.getCaratula()))) &&
            ((this.abogado_actor==null && other.getAbogado_actor()==null) || 
             (this.abogado_actor!=null &&
              this.abogado_actor.equals(other.getAbogado_actor()))) &&
            ((this.abogado_demandante==null && other.getAbogado_demandante()==null) || 
             (this.abogado_demandante!=null &&
              this.abogado_demandante.equals(other.getAbogado_demandante()))) &&
            ((this.movimientos==null && other.getMovimientos()==null) || 
             (this.movimientos!=null &&
              java.util.Arrays.equals(this.movimientos, other.getMovimientos())));
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
        if (getEstado() != null) {
            _hashCode += getEstado().hashCode();
        }
        if (getOrigen() != null) {
            _hashCode += getOrigen().hashCode();
        }
        if (getExpediente() != null) {
            _hashCode += getExpediente().hashCode();
        }
        if (getCaratula() != null) {
            _hashCode += getCaratula().hashCode();
        }
        if (getAbogado_actor() != null) {
            _hashCode += getAbogado_actor().hashCode();
        }
        if (getAbogado_demandante() != null) {
            _hashCode += getAbogado_demandante().hashCode();
        }
        if (getMovimientos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMovimientos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMovimientos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Resultado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:consultaIUEwsdl", "resultado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expediente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "expediente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caratula");
        elemField.setXmlName(new javax.xml.namespace.QName("", "caratula"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abogado_actor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abogado_actor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("abogado_demandante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "abogado_demandante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movimientos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "movimientos"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:consultaIUEwsdl", "giro"));
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
