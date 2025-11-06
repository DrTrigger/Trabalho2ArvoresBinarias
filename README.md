# Checklist – Etapas 1, 2 e 3 (TPA Árvores)

## Etapa 1 – Biblioteca de Árvore Binária
- [x] Criar estrutura `No<T>` (valor, filhoEsquerda, filhoDireita).
- [x] Implementar `ArvoreBinaria<T>` seguindo `IArvoreBinaria<T>`.
- [x] Injeção de `Comparator<T>` no construtor para indexação.
- [x] `adicionar(T)`: inserir respeitando a ordem do Comparator.
- [x] `pesquisar(T)`: busca usando o Comparator da árvore.
- [x] `pesquisar(T, Comparator)`: varredura completa quando o comparator não é o índice.
- [x] `remover(T)`: 0, 1 ou 2 filhos (usar sucessor em-ordem).
- [x] `altura()`: raiz nula = -1; raiz única = 0.
- [x] `quantidadeNos()`: contagem total.
- [x] `caminharEmNivel()`: nível a nível, formato `"[a \n b \n c]"`.
- [x] `caminharEmOrdem()`: esquerda–raiz–direita, formato `"[a \n b \n c]"`.
- [x] Testes mínimos: inserir N itens, checar ordem/altura/quantidade.

## Etapa 2 – Aplicativo (uso da biblioteca)
- [x] Definir domínio: `Aluno(nome, matricula)`.
- [x] Criar `ComparadorAlunoPorMatricula` (índice) e `ComparadorAlunoPorNome` (busca alternativa).
- [x] Fluxos: adicionar, listar em ordem, buscar por matrícula (índice), buscar por nome (Comparator alternativo), remover.
- [x] Saídas: imprimir resultados e caminhamentos.
- [x] Testes com dados variados (ordenados, aleatórios).

## Etapa 3 – Relatório (árvore binária)
- [ ] Baixar projeto do professor e ajustar imports para usar sua biblioteca.
- [ ] Rodar `AppRelatorioArvoreBinaria` (sem alterar lógica do app).
- [ ] Responder Perguntas 1–9 (topologia degenerada vs. balanceada, nós percorridos, O-() de buscas).
- [ ] Descrever o seu aplicativo (Perguntas 10–12): requisitos, arquitetura, planilha de atuação.
- [ ] Incluir nomes do grupo e link do repositório.
- [ ] Exportar em PDF.



# Planilha de Atuação dos Integrantes (Etapas 1–3)

| Tarefa / Entregável                         | Descrição curta                                         | Breno Ricardo | Brayan Mazega | Luiz Felipe Elizeta | Início | Fim   | Status     |
|---------------------------------------------|---------------------------------------------------------|---------------|---------------|---------------------|--------|-------|------------|
| Estrutura `No<T>`                           | Nó com filhos e valor                                   | x             | x             | x                   | -      | -     | feito      |
| `ArvoreBinaria<T>`                          | Classe base com Comparator no construtor                | x             | x             | x                   | -      | -     | feito      |
| `adicionar(T)`                              | Inserção ordenada                                       | x             |               |                     | 02/10  | 02/10 | feito      |
| `pesquisar(T)`                              | Busca pelo índice                                       |               |               | x                   | 06/10  | -     | Em Revisão |
| `pesquisar(T, Comparator)`                  | Busca com Comparator alternativo (varredura)            |               |               | x                   | 06/10  | -     | Em Revisão |
| `remover(T)`                                | Remoção (0/1/2 filhos, sucessor)                        |               |               |                     | 06/10  | -     | Em Revisão |
| `altura()` / `quantidadeNos()`              | Métricas                                                | x             | x             | x                   | 06/10  | -     | Em Revisão |
| `caminharEmNivel()` / `caminharEmOrdem()`   | Saídas formatadas                                       | x             |               |                     | 06/10  | -     | Em Revisão |
| App – fluxos principais                     | Add, listar, buscar por matrícula/nome, remover         |               |               |                     |        |       |            |
| App – testes                                | Dados crescentes/aleatórios                             |               |               |                     |        |       |            |
| Integração com app do professor             | Ajuste de imports + execução                            |               |               |                     |        |       |            |
| Relatório – Q1–Q3                           | Degenerada: topologia, pior caso, O-()                  |               |               |                     |        |       |            |
| Relatório – Q4–Q6                           | Balanceada: topologia, pior caso, O-()                  |               |               |                     |        |       |            |
| Relatório – Q7–Q9                           | Busca com Comparator alternativo + desempenho           |               |               |                     |        |       |            |
| Relatório – Q10–Q12                         | App: requisitos, arquitetura, planilha de atuação       |               |               |                     |        |       |            |
| Revisão final + PDF                         | Revisão, nomes do grupo, link do repositório            |               |               |                     |        |       |            |






# Relatório – Etapas 4, 5 e 6 (AVL e Pesquisa na Biblioteca Padrão do Java)

**Projeto:** Biblioteca de Árvores (BST e AVL) + Aplicativos de Relatório  
**Etapas cobertas:** **4 – ArvoreAVL**, **5 – Relatório AVL**, **6 – Pesquisa (Java)**  
**App utilizado na etapa 5:** `public class AppRelatorioAVL` (fornecido pelo professor)

**Grupo:** Breno Ricardo; Brayan Mazega; Luiz Felipe Elizeta

---

## Etapa 4 — Desenvolvimento da classe **ArvoreAVL**

### Objetivo
Acrescentar à biblioteca uma árvore **AVL** que **herda** de `ArvoreBinaria<T>`, **sobrescreve** `adicionar(T)` e mantém a árvore **balanceada** após cada inserção (remoção é **opcional** nesta etapa).

### Arquitetura e decisões
- **Herança:** `ArvoreAVL<T> extends ArvoreBinaria<T>` (mantém o contrato de `IArvoreBinaria<T>` e o uso de `Comparator<T>`).
- **Nó especializado:** `NoAVL<T> extends No<T>` com campo `altura`.  
  - Convenção usada: `altura(null) = -1`; nó folha `= 0`.  
  - **Fator de balanceamento (fb)**: `altura(esq) − altura(dir)`, mantido em `{−1, 0, 1}`.
- **Inserção (override de `adicionar`)**:  
  1. Desce **à esquerda/direita** de acordo com o `Comparator<T>` (duplicatas à **direita**, como na BST).  
  2. **Atualiza alturas** ao subir a recursão.  
  3. **Rebalanceia** com rotações:
     - **LL:** rotação **direita**  
     - **RR:** rotação **esquerda**  
     - **LR:** esq-**dir** (rot. **esq** no filho + **dir** no nó)  
     - **RL:** dir-**esq** (rot. **dir** no filho + **esq** no nó)
- **Compatibilidade:** Caminhamentos, contagem e pesquisa por chave da árvore base continuam válidos.
- **Complexidades esperadas (AVL):**
  - Inserção e busca: **O(log n)** (altura logarítmica garantida).  
  - Caminhamentos: **O(n)**.

### Checklist de implementação
- [x] `ArvoreAVL<T>` herdando de `ArvoreBinaria<T>`  
- [x] Sobrescrita de `adicionar(T)` com balanceamento (atualização de alturas + rotações)  
- [x] Manutenção das demais operações da interface (pesquisa, caminhamentos, altura, contagem)  
- [ ] (Opcional) `remover(T)` com rebalanceamento

---

## Etapa 5 — Relatório sobre Árvores AVL (execução do **AppRelatorioAVL**)

### Procedimento
1. Ajustamos os **imports** do app do professor para apontar para **nossa** biblioteca (`ArvoreAVL`, `GeradorDeArvores`, `Aluno`, `ComparadorAlunoPorMatricula`).  
2. Executamos o `public class AppRelatorioAVL` (sem alterar o código do professor).  
3. O app gera, para **n = 100, 1000, 10000**, duas estruturas: **AVL** e **BST** (degenerada). Em ambas, usa `geraArvoreDegenerada`.

### Resultados observados (saída do programa)

| n     | Altura (AVL) | log₂(n) ~ | Altura (BST degenerada) |
|-------|--------------|-----------|--------------------------|
| 100   | 6            | 6,64      | 99                       |
| 1000  | 9            | 9,97      | 999                      |
| 10000 | 13           | 13,29     | 9999                     |

> A AVL manteve altura **logarítmica** mesmo com inserções em **ordem crescente**; a BST comum degenerou para altura **n−1**.

### Pergunta 1  
**Por que as “degeneradas” no AppRelatorioAVL (com `geraArvoreDegenerada`) têm alturas muito menores do que as degeneradas do relatório anterior?**

**Resposta:** porque a **AVL rebalanceia** a cada inserção com rotações, mantendo o **|fb| ≤ 1** e garantindo **altura Θ(log n)**, independentemente da ordem de entrada. Já a **BST** sem balanceamento degenera quando insere em ordem crescente, ficando com altura ≈ **n−1**.

> Regra prática: Altura da AVL ≤ ~**1,44 · log₂(n)**; altura da BST degenerada ≈ **n − 1**.

### Pergunta 2  
**Qual a ordem de complexidade de buscas em AVL gerada com `geraArvoreDegenerada`?**

**Resposta:** **O(log n)**. A AVL **impede** a degeneração e mantém a altura logarítmica; portanto, a busca percorre **até altura + 1** nós.

---

## Etapa 6 — Pesquisa: estruturas baseadas em árvores na biblioteca padrão do Java

### Estruturas principais (Java SE)

- **`java.util.TreeMap<K,V>` / `java.util.TreeSet<E>`**  
  Implementadas sobre **árvore rubro-negra** (balanceada).  
  - Complexidades: inserção/busca/remoção (**O(log n)**); iteração ordenada (**O(n)**).  
  - Ordenação: natural (`Comparable`) ou customizada (`Comparator` no construtor).  
  - Navegação rica: `first/last`, `floor/ceiling`, `subMap/headMap/tailMap`, `descendingMap/Set` (interfaces `NavigableMap`/`NavigableSet`).

- **`java.util.concurrent.ConcurrentSkipListMap/Set`**  
  Estruturas **concorrentes** ordenadas baseadas em **skip list**.  
  - Complexidade média **O(log n)** nas operações.  
  - Vantagens em cenários multi-thread.

> Observação: `PriorityQueue` usa **heap** (não é árvore de busca); `HashMap/HashSet` são **hash** (não ordenadas).

### Comparação: Java padrão × nossa biblioteca

| Critério | Nossa BST (`ArvoreBinaria`) | Nossa AVL (`ArvoreAVL`) | `TreeMap/TreeSet` (JDK) |
|---|---|---|---|
| Estrutura | BST simples (pode degenerar) | AVL (balanceada) | Rubro-negra (balanceada) |
| Chave/Ordem | `Comparator<T>` injetado | `Comparator<T>` injetado | `Comparator`/`Comparable` |
| Inserção/Busca/Remoção | até **O(n)** (pior caso) | **O(log n)** | **O(log n)** |
| Intervalos (range) | manual | manual | **nativo** (`subMap`, `floor`, etc.) |
| Busca por não-chave | **O(n)** (varredura) | **O(n)** (varredura) | **O(n)** (varredura) |
| Acesso à estrutura | total (didática) | total (didática) | opaco (APIs) |

**A estrutura Java oferece “todos” os métodos?**  
- Cobre as operações principais e ainda fornece **navegação por intervalos** e views ordenadas.  
- Não expõe nós internos; para caminhamentos “formatados” como os da nossa especificação, usamos as iterações do Java para **emular**.

**Pesquisar por atributo que não é a chave?**  
- Em árvores ordenadas por uma chave, pesquisar por **outro** atributo requer **varrer O(n)** ou manter um **índice secundário** (p. ex., outro `TreeMap` por nome).  
- Alternativa: filtrar `values()` com `stream()` (também O(n)).

### Exemplo (Java) — CRUD básico com `TreeMap` e busca por **nome**

> **Obs.:** no nosso projeto, `Aluno(String nome, Integer matricula)` — nome primeiro, matrícula depois.

```java
import java.util.*;
import java.util.stream.*;

class Aluno {
    final int matricula;
    final String nome;
    Aluno(String n, int m) { this.nome = n; this.matricula = m; }
    @Override public String toString() { return nome + " (" + matricula + ")"; }
}

public class DemoTreeMap {
    public static void main(String[] args) {
        // Índice por matrícula (ordenado)
        TreeMap<Integer, Aluno> porMatricula = new TreeMap<>();

        // Inserções (O(log n))
        porMatricula.put(30, new Aluno("Ana", 30));
        porMatricula.put(10, new Aluno("Bruno", 10));
        porMatricula.put(50, new Aluno("Camila", 50));

        // Listagem ordenada (O(n))
        System.out.println("Todos: " + porMatricula.values());

        // Busca por matrícula (O(log n))
        System.out.println("Busca 10: " + porMatricula.get(10));

        // Remoção (O(log n))
        porMatricula.remove(30);

        // Intervalo [11..50] (O(log n + k))
        System.out.println("Intervalo 11..50: " +
            porMatricula.subMap(11, true, 50, true).values());

        // Busca por nome (não indexado): varredura O(n)
        String alvo = "Camila";
        Aluno achado = porMatricula.values().stream()
                .filter(a -> a.nome.equals(alvo))
                .findFirst().orElse(null);
        System.out.println("Busca por nome 'Camila': " + achado);

        // Índice secundário por nome (opcional)
        TreeMap<String, List<Aluno>> porNome = new TreeMap<>();
        for (Aluno a : porMatricula.values()) {
            porNome.computeIfAbsent(a.nome, k -> new ArrayList<>()).add(a);
        }
        System.out.println("Por nome 'Camila' via índice secundário: " + porNome.get("Camila"));
    }
}







