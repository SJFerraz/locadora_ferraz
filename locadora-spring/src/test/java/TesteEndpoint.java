import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class TesteEndpoint {
	
	@GetMapping(path="public/teste")
	public ResponseEntity<?> canAccessPublic(){
		System.out.println("tem acesso ao: public");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="protected/teste")
	public ResponseEntity<?> canAccessProtected(){
		System.out.println("tem acesso ao: protected");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="shared/teste")
	public ResponseEntity<?> canAccessShared(){
		System.out.println("tem acesso ao: shared");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="protected-share/teste")
	public ResponseEntity<?> canAccessProtectedShare(){
		System.out.println("tem acesso ao: protected-share");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(path="private/teste")
	public ResponseEntity<?> canAccessPrivate(){
		System.out.println("tem acesso ao: private");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="admin/teste")
	public ResponseEntity<?> canAccessAdmin(){
		System.out.println("tem acesso ao: admin");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
