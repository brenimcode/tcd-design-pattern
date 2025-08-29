# PPP


## Como rodar o JOGO Space Invaders:


#### Compile o Framework (Se ainda não o fez)

- Compila todos os arquivos .java das pastas src/spriteframework/ e src/spriteframework/sprite/
Coloca os .class gerados na pasta bin, mantendo a estrutura de pacotes.

```bash
javac -d bin src/spriteframework/*.java src/spriteframework/sprite/*.java
```


#### Compile a Implementação (Se ainda não o fez)

```bash
    javac -cp bin -d bin src/spaceinvaders/*.java src/spaceinvaders/sprite/*.java
```

- `-cp bin` diz ao compilador para procurar as classes já compiladas na pasta bin.
Isso é importante porque, ao compilar os arquivos de spaceinvaders, eles podem depender de classes do framework (que já estão em bin), como spriteframework.AbstractBoard,


### Executar o Jogo

- Vá para o Space Invaders:

```bash
cd Arcade-Sprite-Based-Space-Invaders/Arcade-Sprite-Based-Space-Invaders
```

- Agora, execute:

```bash
java -cp "bin:../../Arcade-Sprite-Based-Framework/Arcade-Sprite-Based-Framework/bin" spaceinvaders.SpaceInvadersGame
```

- '-cp' Indica: classpath, que é a lista de diretórios e arquivos JAR que o Java deve pesquisar para encontrar classes.