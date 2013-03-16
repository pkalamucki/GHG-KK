package strategy.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

	private final static QName _StrategyRoot_QNAME = new QName("",
			"StrategyRoot");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: generated
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link StrategyRoot }
	 * 
	 */
	public StrategyRoot createStrategyRoot() {
		return new StrategyRoot();
	}

	/**
	 * Create an instance of {@link ZIP }
	 * 
	 */
	public ZIP createZIP() {
		return new ZIP();
	}

	/**
	 * Create an instance of {@link Pack }
	 * 
	 */
	public Pack createPack() {
		return new Pack();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link StrategyRoot }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "StrategyRoot")
	public JAXBElement<StrategyRoot> createStrategyRoot(StrategyRoot value) {
		return new JAXBElement<StrategyRoot>(_StrategyRoot_QNAME,
				StrategyRoot.class, null, value);
	}

}
