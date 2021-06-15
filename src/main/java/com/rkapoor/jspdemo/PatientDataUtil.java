package com.rkapoor.jspdemo;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Address.AddressUse;
import org.hl7.fhir.r4.model.BaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.util.BundleUtil;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Patient.ContactComponent;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.StructureDefinition;

import java.io.IOException;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ca.uhn.fhir.model.primitive.IdDt;

import com.rkapoor.jspdemo.PatientClass;

public class PatientDataUtil {

	PatientClass patientObj;

	@SuppressWarnings("unlikely-arg-type")
	public List<PatientClass> ParsePatientData(String resourceBody) {

		List<PatientClass> patients = new ArrayList<>();

		// Create a context
		FhirContext ctx = FhirContext.forR4();

		// Create a JSON parser
		IParser parser = ctx.newJsonParser();

		Bundle bundle = ctx.newJsonParser().parseResource(Bundle.class, resourceBody);
		System.out.println("NO of Patients :::::::::::::: " + bundle.getTotal() + "----" + resourceBody);
		for (BundleEntryComponent entry : bundle.getEntry()) {

			System.out.println(entry.getFullUrl());

			if (entry.getResource().getResourceType().equals(ResourceType.Patient)) {
				Patient pat = (Patient) entry.getResource();

				String id = "";
				Boolean active = false;
				String PatientName = "";
				String gender = "";
				String address = "";
				String email = "";
				String birthDate ;
				String phone = "";
				String generalPractitioner = "";

				id = pat.getIdElement().getIdPart();
				active = pat.getActive();
				List<HumanName> name = pat.getName();

				System.out.println(name.get(0).getGivenAsSingleString() + " " + name.get(0).getFamily());
				if (name.size() > 0) {
	
					PatientName = name.get(0).getGivenAsSingleString() + " " + name.get(0).getFamily();
				}

				List<ContactPoint> tel = pat.getTelecom();

				if (tel.size() > 0) {
					if (tel.get(0).getSystem().equals(ContactPoint.ContactPointSystem.PHONE)) {
						phone = tel.get(0).getValue().toString();}
					if (tel.get(0).getSystem().equals(ContactPoint.ContactPointSystem.EMAIL)) {
						email = tel.get(0).getValue().toString();}
				}

				gender = pat.getGender().toString();

				birthDate = pat.getBirthDate().toLocaleString().toString();
				List<Address> addr = pat.getAddress();
				if (addr.size() > 0) {
					System.out.println(addr.get(0).getLine().toString());
					address = (addr.get(0).getLine().toString() + " " + addr.get(0).getCity() + ", "
							+ addr.get(0).getState() + ", " + addr.get(0).getCountry() + " "
							+ addr.get(0).getPostalCode());
				}
				generalPractitioner = pat.getGeneralPractitionerFirstRep().getReference();

				patients.add(new PatientClass(id, active, PatientName, gender, address, email, birthDate, phone,
						generalPractitioner));

			}
		}

		// return the list
		return patients;

	}

	private static void generatePatientJson(FhirContext ctx) {
		Patient patient = new Patient();

		// FIRST AND LAST NAME
		patient.addName().setFamily("Duck").addGiven("Donald");
		// SOCIAL SECURITY NUMBER
		// https://www.hl7.org/FHIR/datatypes.html#Identifier
		// https://www.hl7.org/FHIR/identifier-registry.html

		patient.addIdentifier()
				.setType(new CodeableConcept()
						.addCoding(new Coding().setCode("SB").setSystem("http://hl7.org/fhir/v2/0203")))
				.setSystem("http://hl7.org/fhir/sid/us-ssn").setValue("123456789");

		// GENDER
		patient.setGender(AdministrativeGender.FEMALE);

		// ADDRESS INFORMATION
		patient.addAddress().setUse(AddressUse.HOME).addLine("Street name, number, direction & P.O. Box etc.")
				.setCity("Name of city, town etc.").setState("Sub-unit of country (abbreviations ok)")
				.setPostalCode("Postal/ZIP code for area");

		// CONTACT https://www.hl7.org/fhir/datatypes-examples.html#ContactPoint
		patient.addTelecom().setSystem(ContactPointSystem.PHONE).setValue("(555) 675 5745");

		patient.addTelecom().setSystem(ContactPointSystem.PHONE).setValue("(415) 675 5745");

		patient.addTelecom().setSystem(ContactPointSystem.EMAIL).setValue("test@test.com");

		// EMERGENCY CONTACT
		// https://www.hl7.org/FHIR/patient-definitions.html#Patient.contact
		ContactComponent emergencyContact = new ContactComponent();

		emergencyContact.addTelecom().setSystem(ContactPointSystem.PHONE).setValue("(111) 675 5745");

		// Relationship to patient
		emergencyContact.addRelationship().addCoding().setSystem("http://hl7.org/fhir/ValueSet/v2-0131").setCode("C");

		emergencyContact.setName(new HumanName().setFamily("Duck Emergency contact").addGiven("Duke"));

		patient.addContact(emergencyContact);

		// Encode to JSON
		IParser jsonParser = ctx.newJsonParser();
		jsonParser.setPrettyPrint(true);
		String encoded = jsonParser.encodeResourceToString(patient);
		System.out.println(encoded);
	}

}
