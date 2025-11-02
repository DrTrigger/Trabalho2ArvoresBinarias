package org.tpa;

import org.tpa.model.Aluno;

import java.util.*;



import java.util.List;
import java.util.TreeMap;

public class TesteAVL {
    public static void main(String[] args) {
        // Índice por matrícula (ordem crescente)
        TreeMap<Integer, Aluno> porMatricula = new TreeMap<>();

        // Cadastrar (put) O(log n)
        porMatricula.put(30, new Aluno("Ana", 30));
        porMatricula.put(10, new Aluno("Bruno", 10));
        porMatricula.put(50, new Aluno("Camila", 50));

        // Listar ordenado (in-order): O(n)
        System.out.println("Todos: " + porMatricula.values());

        // Pesquisar por matrícula (get) O(log n)
        System.out.println("Busca 10: " + porMatricula.get(10));

        // Remover por matrícula (remove) O(log n)
        porMatricula.remove(30);

        // Navegação de intervalo: [11..50] O(log n + k)
        System.out.println("Intervalo 11..50: " + porMatricula.subMap(11, true, 50, true).values());

        // Buscar por nome (não indexado): varredura O(n)
        String alvo = "Camila";
        Aluno achado = porMatricula.values().stream()
                .filter(a -> a.getNome().equals(alvo))
                .findFirst().orElse(null);
        System.out.println("Busca por nome 'Camila': " + achado);

        // Índice secundário por nome (se necessário, manutenção duplicada)
        TreeMap<String, List<Aluno>> porNome = new TreeMap<>();
        for (Aluno a : porMatricula.values()) {
            porNome.computeIfAbsent(a.getNome(), k -> new ArrayList<>()).add(a);
        }
        System.out.println("Por nome 'Camila' via índice secundário: " + porNome.get("Camila"));
    }
}
