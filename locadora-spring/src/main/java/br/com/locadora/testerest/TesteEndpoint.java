package br.com.locadora.testerest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class TesteEndpoint {
	
	@GetMapping(path="public/teste")
	public ResponseEntity<?> canAccessPublic(Authentication authentication){
		System.out.println(authentication.getPrincipal()+" - "+authentication.getAuthorities());
		System.out.println("tem acesso ao: public");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="protected/teste")
	public ResponseEntity<?> canAccessProtected(Authentication authentication){
		System.out.println(authentication.getPrincipal()+" - "+authentication.getAuthorities());
		System.out.println("tem acesso ao: protected");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="shared/teste")
	public ResponseEntity<?> canAccessShared(Authentication authentication){
		System.out.println(authentication.getPrincipal()+" - "+authentication.getAuthorities());
		System.out.println("tem acesso ao: shared");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="protected-share/teste")
	public ResponseEntity<?> canAccessProtectedShare(Authentication authentication){
		System.out.println(authentication.getPrincipal()+" - "+authentication.getAuthorities());
		System.out.println("tem acesso ao: protected-share");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(path="private/teste")
	public ResponseEntity<?> canAccessPrivate(Authentication authentication){
		System.out.println(authentication.getPrincipal()+" - "+authentication.getAuthorities());
		System.out.println("tem acesso ao: private");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path="admin/teste")
	public ResponseEntity<?> canAccessAdmin(Authentication authentication){
		System.out.println(authentication.getPrincipal()+" - "+authentication.getAuthorities());
		System.out.println("tem acesso ao: admin");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
