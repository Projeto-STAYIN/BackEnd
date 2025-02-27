package school.sptech.STAYIN.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.STAYIN.Enum.Status;
import school.sptech.STAYIN.model.Quarto;
import school.sptech.STAYIN.repository.QuartoRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    private QuartoRepository repository;

    @PostMapping
    public ResponseEntity<Quarto> cadastrar(@RequestBody Quarto quarto){
        Quarto quartoSalvo = repository.save(quarto);
        return ResponseEntity.status(201).body(quartoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> listar(){
        List<Quarto> quartos = repository.findAll();

        if (quartos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(quartos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.of(repository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> atualizar(@PathVariable Integer id,@RequestBody Quarto quartoParaAlterar){
        if (repository.existsById(id)){
            quartoParaAlterar.setId(id);
            Quarto quartoAtualizado = repository.save(quartoParaAlterar);
            return ResponseEntity.status(200).body(quartoAtualizado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/numero")
    public ResponseEntity<Quarto> buscarPorNumeroDoQuarto(@RequestParam Integer numero){
        Optional<Quarto> quarto = repository.findByNumero(numero);

        return ResponseEntity.of(quarto);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Quarto>> buscarPorSatus(@RequestParam Status status){
        List<Quarto> quartos = repository.findByStatus(status);

        if (quartos.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(quartos);
    }
}