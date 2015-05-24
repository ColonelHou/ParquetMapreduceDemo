/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package nk.parquet_tut.avro;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Address extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Address\",\"namespace\":\"nk.parquet_tut.avro\",\"fields\":[{\"name\":\"street\",\"type\":\"string\"},{\"name\":\"city\",\"type\":\"string\"},{\"name\":\"state\",\"type\":\"string\"},{\"name\":\"country\",\"type\":\"string\"},{\"name\":\"zip\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence street;
  @Deprecated public java.lang.CharSequence city;
  @Deprecated public java.lang.CharSequence state;
  @Deprecated public java.lang.CharSequence country;
  @Deprecated public int zip;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Address() {}

  /**
   * All-args constructor.
   */
  public Address(java.lang.CharSequence street, java.lang.CharSequence city, java.lang.CharSequence state, java.lang.CharSequence country, java.lang.Integer zip) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.country = country;
    this.zip = zip;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return street;
    case 1: return city;
    case 2: return state;
    case 3: return country;
    case 4: return zip;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: street = (java.lang.CharSequence)value$; break;
    case 1: city = (java.lang.CharSequence)value$; break;
    case 2: state = (java.lang.CharSequence)value$; break;
    case 3: country = (java.lang.CharSequence)value$; break;
    case 4: zip = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'street' field.
   */
  public java.lang.CharSequence getStreet() {
    return street;
  }

  /**
   * Sets the value of the 'street' field.
   * @param value the value to set.
   */
  public void setStreet(java.lang.CharSequence value) {
    this.street = value;
  }

  /**
   * Gets the value of the 'city' field.
   */
  public java.lang.CharSequence getCity() {
    return city;
  }

  /**
   * Sets the value of the 'city' field.
   * @param value the value to set.
   */
  public void setCity(java.lang.CharSequence value) {
    this.city = value;
  }

  /**
   * Gets the value of the 'state' field.
   */
  public java.lang.CharSequence getState() {
    return state;
  }

  /**
   * Sets the value of the 'state' field.
   * @param value the value to set.
   */
  public void setState(java.lang.CharSequence value) {
    this.state = value;
  }

  /**
   * Gets the value of the 'country' field.
   */
  public java.lang.CharSequence getCountry() {
    return country;
  }

  /**
   * Sets the value of the 'country' field.
   * @param value the value to set.
   */
  public void setCountry(java.lang.CharSequence value) {
    this.country = value;
  }

  /**
   * Gets the value of the 'zip' field.
   */
  public java.lang.Integer getZip() {
    return zip;
  }

  /**
   * Sets the value of the 'zip' field.
   * @param value the value to set.
   */
  public void setZip(java.lang.Integer value) {
    this.zip = value;
  }

  /** Creates a new Address RecordBuilder */
  public static nk.parquet_tut.avro.Address.Builder newBuilder() {
    return new nk.parquet_tut.avro.Address.Builder();
  }
  
  /** Creates a new Address RecordBuilder by copying an existing Builder */
  public static nk.parquet_tut.avro.Address.Builder newBuilder(nk.parquet_tut.avro.Address.Builder other) {
    return new nk.parquet_tut.avro.Address.Builder(other);
  }
  
  /** Creates a new Address RecordBuilder by copying an existing Address instance */
  public static nk.parquet_tut.avro.Address.Builder newBuilder(nk.parquet_tut.avro.Address other) {
    return new nk.parquet_tut.avro.Address.Builder(other);
  }
  
  /**
   * RecordBuilder for Address instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Address>
    implements org.apache.avro.data.RecordBuilder<Address> {

    private java.lang.CharSequence street;
    private java.lang.CharSequence city;
    private java.lang.CharSequence state;
    private java.lang.CharSequence country;
    private int zip;

    /** Creates a new Builder */
    private Builder() {
      super(nk.parquet_tut.avro.Address.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(nk.parquet_tut.avro.Address.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.street)) {
        this.street = data().deepCopy(fields()[0].schema(), other.street);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.city)) {
        this.city = data().deepCopy(fields()[1].schema(), other.city);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.state)) {
        this.state = data().deepCopy(fields()[2].schema(), other.state);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.zip)) {
        this.zip = data().deepCopy(fields()[4].schema(), other.zip);
        fieldSetFlags()[4] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Address instance */
    private Builder(nk.parquet_tut.avro.Address other) {
            super(nk.parquet_tut.avro.Address.SCHEMA$);
      if (isValidValue(fields()[0], other.street)) {
        this.street = data().deepCopy(fields()[0].schema(), other.street);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.city)) {
        this.city = data().deepCopy(fields()[1].schema(), other.city);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.state)) {
        this.state = data().deepCopy(fields()[2].schema(), other.state);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.country)) {
        this.country = data().deepCopy(fields()[3].schema(), other.country);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.zip)) {
        this.zip = data().deepCopy(fields()[4].schema(), other.zip);
        fieldSetFlags()[4] = true;
      }
    }

    /** Gets the value of the 'street' field */
    public java.lang.CharSequence getStreet() {
      return street;
    }
    
    /** Sets the value of the 'street' field */
    public nk.parquet_tut.avro.Address.Builder setStreet(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.street = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'street' field has been set */
    public boolean hasStreet() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'street' field */
    public nk.parquet_tut.avro.Address.Builder clearStreet() {
      street = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'city' field */
    public java.lang.CharSequence getCity() {
      return city;
    }
    
    /** Sets the value of the 'city' field */
    public nk.parquet_tut.avro.Address.Builder setCity(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.city = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'city' field has been set */
    public boolean hasCity() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'city' field */
    public nk.parquet_tut.avro.Address.Builder clearCity() {
      city = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'state' field */
    public java.lang.CharSequence getState() {
      return state;
    }
    
    /** Sets the value of the 'state' field */
    public nk.parquet_tut.avro.Address.Builder setState(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.state = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'state' field has been set */
    public boolean hasState() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'state' field */
    public nk.parquet_tut.avro.Address.Builder clearState() {
      state = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'country' field */
    public java.lang.CharSequence getCountry() {
      return country;
    }
    
    /** Sets the value of the 'country' field */
    public nk.parquet_tut.avro.Address.Builder setCountry(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.country = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'country' field has been set */
    public boolean hasCountry() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'country' field */
    public nk.parquet_tut.avro.Address.Builder clearCountry() {
      country = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'zip' field */
    public java.lang.Integer getZip() {
      return zip;
    }
    
    /** Sets the value of the 'zip' field */
    public nk.parquet_tut.avro.Address.Builder setZip(int value) {
      validate(fields()[4], value);
      this.zip = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'zip' field has been set */
    public boolean hasZip() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'zip' field */
    public nk.parquet_tut.avro.Address.Builder clearZip() {
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    public Address build() {
      try {
        Address record = new Address();
        record.street = fieldSetFlags()[0] ? this.street : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.city = fieldSetFlags()[1] ? this.city : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.state = fieldSetFlags()[2] ? this.state : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.country = fieldSetFlags()[3] ? this.country : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.zip = fieldSetFlags()[4] ? this.zip : (java.lang.Integer) defaultValue(fields()[4]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}