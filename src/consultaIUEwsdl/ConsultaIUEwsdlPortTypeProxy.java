package consultaIUEwsdl;
import java.rmi.RemoteException;

import consultaIUEwsdl_pkg.*;

public class ConsultaIUEwsdlPortTypeProxy implements ConsultaIUEwsdlPortType {
  private String _endpoint = null;
  private ConsultaIUEwsdlPortType consultaIUEwsdlPortType = null;
  
  public ConsultaIUEwsdlPortTypeProxy() {
    _initConsultaIUEwsdlPortTypeProxy();
  }
  
  public ConsultaIUEwsdlPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initConsultaIUEwsdlPortTypeProxy();
  }
  
  private void _initConsultaIUEwsdlPortTypeProxy() {
    try {
      consultaIUEwsdlPortType = (new ConsultaIUEwsdlLocator()).getconsultaIUEwsdlPort();
      if (consultaIUEwsdlPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)consultaIUEwsdlPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)consultaIUEwsdlPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (consultaIUEwsdlPortType != null)
      ((javax.xml.rpc.Stub)consultaIUEwsdlPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ConsultaIUEwsdlPortType getConsultaIUEwsdlPortType() {
    if (consultaIUEwsdlPortType == null)
      _initConsultaIUEwsdlPortTypeProxy();
    return consultaIUEwsdlPortType;
  }

	@Override
	public Resultado consultaIUE(String iue) throws RemoteException {
		return consultaIUEwsdlPortType.consultaIUE(iue);
	}
	  
  
}