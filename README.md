# Checklist – Etapas 1, 2 e 3 (TPA Árvores)

## Etapa 1 – Biblioteca de Árvore Binária
- [x] Criar estrutura `No<T>` (valor, filhoEsquerda, filhoDireita).
- [x] Implementar `ArvoreBinaria<T>` seguindo `IArvoreBinaria<T>`.
- [x] Injeção de `Comparator<T>` no construtor para indexação.
- [x] `adicionar(T)`: inserir respeitando a ordem do Comparator.
- [ ] `pesquisar(T)`: busca usando o Comparator da árvore.
- [ ] `pesquisar(T, Comparator)`: varredura completa quando o comparator não é o índice.
- [ ] `remover(T)`: 0, 1 ou 2 filhos (usar sucessor em-ordem).
- [ ] `altura()`: raiz nula = -1; raiz única = 0.
- [ ] `quantidadeNos()`: contagem total.
- [ ] `caminharEmNivel()`: nível a nível, formato `"[a \n b \n c]"`.
- [ ] `caminharEmOrdem()`: esquerda–raiz–direita, formato `"[a \n b \n c]"`.
- [ ] Testes mínimos: inserir N itens, checar ordem/altura/quantidade.

## Etapa 2 – Aplicativo (uso da biblioteca)
- [x] Definir domínio: `Aluno(nome, matricula)`.
- [x] Criar `ComparadorAlunoPorMatricula` (índice) e `ComparadorAlunoPorNome` (busca alternativa).
- [ ] Fluxos: adicionar, listar em ordem, buscar por matrícula (índice), buscar por nome (Comparator alternativo), remover.
- [ ] Saídas: imprimir resultados e caminhamentos.
- [ ] Testes com dados variados (ordenados, aleatórios).

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
| `altura()` / `quantidadeNos()`              | Métricas                                                |               |               |                     |        |       |            |
| `caminharEmNivel()` / `caminharEmOrdem()`   | Saídas formatadas                                       |               |               | x                   | 06/10  | -     | Em Revisão |
| App – fluxos principais                     | Add, listar, buscar por matrícula/nome, remover         |               |               |                     |        |       |            |
| App – testes                                | Dados crescentes/aleatórios                             |               |               |                     |        |       |            |
| Integração com app do professor             | Ajuste de imports + execução                            |               |               |                     |        |       |            |
| Relatório – Q1–Q3                           | Degenerada: topologia, pior caso, O-()                  |               |               |                     |        |       |            |
| Relatório – Q4–Q6                           | Balanceada: topologia, pior caso, O-()                  |               |               |                     |        |       |            |
| Relatório – Q7–Q9                           | Busca com Comparator alternativo + desempenho           |               |               |                     |        |       |            |
| Relatório – Q10–Q12                         | App: requisitos, arquitetura, planilha de atuação       |               |               |                     |        |       |            |
| Revisão final + PDF                         | Revisão, nomes do grupo, link do repositório            |               |               |                     |        |       |            |











