@Service
public class DoctorService {
	@AutoWired
	private DoctorRepository repository;
	
	public Optional<Doctor> findById(Integer id){
		return this.repository.findByDoctorId(id);
	}
	
	public DoctorDTO storeDoctorData(DoctorDTO dto) {
		Doctor doctor = DoctorMapper.fromDTOToEntity(dto);
		Optional<Doctor> existingUser = this.repository.findByDoctorId(doctor.getDoctorId());
		
		if(!existingUser.isPresent()) {
			doctor = this.repository.save(doctor);
			return DoctorMapper.fromEntityToDto(doctor);
		}
		
		return UserMapper.fromEntityToDTO(existingUser.get());
	}
	
}
