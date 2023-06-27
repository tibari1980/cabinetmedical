package com.arcesi.cliniquemedical.jdd;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.cliniquemedical.entities.AdresseBean;
import com.arcesi.cliniquemedical.entities.AssistantMedicoSocialeBean;
import com.arcesi.cliniquemedical.entities.MedecinBean;
import com.arcesi.cliniquemedical.entities.MedicamentBean;
import com.arcesi.cliniquemedical.entities.PathologieBean;
import com.arcesi.cliniquemedical.entities.PatientBean;
import com.arcesi.cliniquemedical.entities.SecretaireBean;
import com.arcesi.cliniquemedical.enums.SexEnumeration;
import com.arcesi.cliniquemedical.enums.SpecialiteMedecinEnumeration;
import com.arcesi.cliniquemedical.repositories.AssistanteMedicoSocialeRepository;
import com.arcesi.cliniquemedical.repositories.MedecinRepository;
import com.arcesi.cliniquemedical.repositories.MedicamentRepository;
import com.arcesi.cliniquemedical.repositories.PathologieRepository;
import com.arcesi.cliniquemedical.repositories.PatientRepository;
import com.arcesi.cliniquemedical.repositories.SecretaireRepository;
import com.arcesi.cliniquemedical.security.enums.PathologieEnums;
import com.arcesi.cliniquemedical.utils.Utils;

@Component
@Transactional
public class JddTestCabinetMedical implements CommandLineRunner {

	@Autowired
	private PathologieRepository pathologieRepository;
	@Autowired
	private MedicamentRepository medicamentRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private AssistanteMedicoSocialeRepository assistanteMedicoSocialeRepository;
	@Autowired
	private MedecinRepository medecinRepository;
	@Autowired
	private SecretaireRepository secretaireRepository;

	@Override
	public void run(String... args) throws Exception {

		addPathologie();
		addMedicaments();
		addPatient();
		addMedecin();
		addAssistanteMedicoSocial();
		addSecretaire();

	}

	private void addSecretaire() {
		secretaireRepository.deleteAllInBatch();
		SecretaireBean hmaza = SecretaireBean.builder().nomSecretaire("zeroual").prenomSecretaire("hamza")
				.adresseBean(new AdresseBean(11, "rue de paris", "75015", "paris", "france"))
				.dateNaissance(LocalDate.of(1988, 01, 14)).emailSecretaire("hamzazeroual@gmail.com")
				.sex(SexEnumeration.HOMME).cinSecretaire("DTES56987").telephoneSecretaire("0625417847")
                
				.nssSecretaire("125468795478544").uidSecretaire(UUID.randomUUID().toString()).departementNaissance("99")
				.villeNaissance("CASABLANCE").paysNaissance("MAROC").photoSecretaire("hamza.jpeg").build();
		 hmaza.setAgeSecretaire(Period.between(hmaza.getDateNaissance(),LocalDate.now()).getYears());
		SecretaireBean hajar = SecretaireBean.builder().nomSecretaire("kazzar").prenomSecretaire("hajar")
				.adresseBean(new AdresseBean(11, "rue de paris", "75015", "paris", "france"))
				.dateNaissance(LocalDate.of(2002, 01, 14)).emailSecretaire("kazzarlyna@gmail.com")
				.telephoneSecretaire("062549854784").cinSecretaire("DTES56998").sex(SexEnumeration.FEMME)
				.nssSecretaire("9875462154782").uidSecretaire(UUID.randomUUID().toString()).departementNaissance("75")
				.villeNaissance("CASABLANCA").paysNaissance("MAROC").photoSecretaire("hajar.jpeg").build();
        hajar.setAgeSecretaire(Period.between(hajar.getDateNaissance(), LocalDate.now()).getYears());		secretaireRepository.saveAll(Arrays.asList(hajar, hmaza));
	}

	private void addAssistanteMedicoSocial() {
		assistanteMedicoSocialeRepository.deleteAllInBatch();
		for (int i = 1; i < 15; i++) {
			AssistantMedicoSocialeBean assis = AssistantMedicoSocialeBean.builder().firstName("kazzar").lastName("lyna")
					.telephoneFix("0145784584" + i).telephonePortable("062578784570")
					.uidAsm(UUID.randomUUID().toString())
					.dateNaissance(LocalDate.of(Utils.genererInt(1935, 2010), Utils.genererInt(1, 10),
							Utils.genererInt(1, 28)))
					.email("albertduvalassistante" + i + "@gmail.com").photo("assistane.jpeg")
					.adresseBean(new AdresseBean(100 + i, "RAYMOND LOSSERAND", "750" + i, "LILLE", "FRANCE")).build();
			
			assistanteMedicoSocialeRepository.save(assis);
		}

	}

	private void addMedecin() {
		medecinRepository.deleteAllInBatch();
		for (int i = 1; i < 15; i++) {
			MedecinBean medecin = MedecinBean.builder().firstName("eric").lastName("albert")
					.telephoneFix("0145784574" + i).telephonePortable("062548784570")
					.codeMedecinUnique(UUID.randomUUID().toString())
					.numeroAgrement("TEXT458754" + Utils.genererInt(10, 18142))
					.specialiteMedecin(SpecialiteMedecinEnumeration.GENERALISTE.getId())
					.dateNaissance(LocalDate.of(Utils.genererInt(1935, 2010), Utils.genererInt(1, 10),
							Utils.genererInt(1, 28)))
					.emailMedecin("ttibarinewdez" + i + "@gmail.com").photoMedecin("medecin.jpeg").build();
			medecinRepository.save(medecin);
		}

	}

	private void addPatient() {
		patientRepository.deleteAllInBatch();
		for (int i = 1; i < 30; i++) {
			PatientBean patient = PatientBean.builder().nomPatient("zeroual").prenomPatient("tibari")
					.sex(Utils.genererInt(1, 10) % 2 == 0 ? SexEnumeration.FEMME : SexEnumeration.HOMME)
					.adresseBean(new AdresseBean(1 + 1, "rue peguy", "75002", "paris", "france")).agePatient(17 + i)
					.uidPatient(UUID.randomUUID().toString()).cinPatient("TEXT5487921" + i)
					.dateNaissance(LocalDate.of(Utils.genererInt(1935, 2010), Utils.genererInt(1, 10),
							Utils.genererInt(1, 28)))
					.telephonePatient("06254871" + Utils.genererInt(20, 99)).photoPatient("photoPatient" + i + ".jpeg")
					.paysNaissance("France").villeNaissance("Paris").nssPatient("998487542145781" + i)
					.emailPatient("tibarinewdez" + i + "@gmail.com").build();
			patient.setAgePatient(Period.between(patient.getDateNaissance(), LocalDate.now()).getYears());
		
			patientRepository.save(patient);
		}

	}

	private void addMedicaments() {
		medicamentRepository.deleteAllInBatch();
		for (int i = 0; i < 20; i++) {
			Long id = Long.valueOf(Utils.genererInt(1, pathologieRepository.findAll().size()));
			PathologieBean path = pathologieRepository.findById(id).get();
			MedicamentBean doliprane = MedicamentBean.builder().codeUniqueMedicament(UUID.randomUUID().toString())
					.designationMedicament("seroplam" + i).descriptionMedicament("description seroplam" + i).build();
			doliprane.getPathologieBeans().add(path);
			medicamentRepository.save(doliprane);

		}

	}

	private void addPathologie() {
		pathologieRepository.deleteAllInBatch();
		PathologieBean cardiopathie = PathologieBean.builder().codeUniquePathologie(UUID.randomUUID().toString())
				.nomPathologie(PathologieEnums.CARDIOPATHIE.getId()).descriptionPathologie("description test all")
				.build();
		PathologieBean diabete = PathologieBean.builder().codeUniquePathologie(UUID.randomUUID().toString())
				.nomPathologie(PathologieEnums.DIABETE.getId()).build();
		PathologieBean cataracte = PathologieBean.builder().codeUniquePathologie(UUID.randomUUID().toString())
				.nomPathologie(PathologieEnums.CATARACTE.getId()).descriptionPathologie("description test all").build();
		PathologieBean depression = PathologieBean.builder().codeUniquePathologie(UUID.randomUUID().toString())
				.nomPathologie(PathologieEnums.DEPRESSION.getId()).descriptionPathologie("description test all")
				.build();

		PathologieBean encephalite = PathologieBean.builder().codeUniquePathologie(UUID.randomUUID().toString())
				.nomPathologie(PathologieEnums.ENCEPHALITE.getId()).descriptionPathologie("description test all")
				.build();
		PathologieBean fibrillation = PathologieBean.builder().codeUniquePathologie(UUID.randomUUID().toString())
				.nomPathologie(PathologieEnums.FIBRILLATION.getId()).build();
		PathologieBean hepatite = PathologieBean.builder().codeUniquePathologie(UUID.randomUUID().toString())
				.nomPathologie(PathologieEnums.HEPATITE.getId()).descriptionPathologie("description test all").build();

		pathologieRepository
				.saveAll(Arrays.asList(fibrillation, hepatite, diabete, depression, encephalite, cardiopathie));
	}

}
