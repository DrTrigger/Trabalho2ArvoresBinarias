# Checklist – Etapas 1, 2 e 3 (TPA Árvores)

## Etapa 1 – Biblioteca de Árvore Binária
- [ ] Criar estrutura `No<T>` (valor, filhoEsquerda, filhoDireita).
- [ ] Implementar `ArvoreBinaria<T>` seguindo `IArvoreBinaria<T>`.
- [ ] Injeção de `Comparator<T>` no construtor para indexação.
- [ ] `adicionar(T)`: inserir respeitando a ordem do Comparator.
- [ ] `pesquisar(T)`: busca usando o Comparator da árvore.
- [ ] `pesquisar(T, Comparator)`: varredura completa quando o comparator não é o índice.
- [ ] `remover(T)`: 0, 1 ou 2 filhos (usar sucessor em-ordem).
- [ ] `altura()`: raiz nula = -1; raiz única = 0.
- [ ] `quantidadeNos()`: contagem total.
- [ ] `caminharEmNivel()`: nível a nível, formato `"[a \n b \n c]"`.
- [ ] `caminharEmOrdem()`: esquerda–raiz–direita, formato `"[a \n b \n c]"`.
- [ ] Testes mínimos: inserir N itens, checar ordem/altura/quantidade.

## Etapa 2 – Aplicativo (uso da biblioteca)
- [ ] Definir domínio: `Aluno(nome, matricula)`.
- [ ] Criar `ComparadorAlunoPorMatricula` (índice) e `ComparadorAlunoPorNome` (busca alternativa).
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



  # Organização do Grupo e Divisão de Tarefas

| Tarefa                                    | Descrição resumida                                 | Breno Ricardo | Brayan Mazega | Luiz Felipe Elizeta | Status   | Prazo   |
|-------------------------------------------|---------------------------------------------------|---------------|---------------|----------------------|----------|---------|
| Implementar bibliotecas de árvores binárias| Criar classes, métodos e operações básicas         |               |               |                      |          |         |
| Implementar biblioteca AVL                 | Adicionar balanceamento AVL e validar inserções    |               |               |                      |          |         |
| Aplicar bibliotecas no sistema             | Integrar ao sistema e testar com dados             |               |               |                      |          |         |
| Analisar topologia e complexidade          | Comparar desempenho entre árvores balanceadas/não  |               |               |                      |          |         |
| Pesquisar estruturas Java                  | Levantar estruturas nativas de árvores em Java     |               |               |                      |          |         |
| Elaborar planilha de responsabilidades     | Manter atualizado o registro do grupo              |               |               |                      |          |         |
| Redigir relatório final                    | Organizar análises, resultados e conclusões        |               |               |                      |          |         |
| Organização inicial                        | Ler especificação e definir plano de ação          |               |               |                      |          |         |
| Divisão de tarefas                         | Distribuir etapas entre os integrantes             |               |               |                      |          |         |







