# IMC Calculator - Documentation
# Calculadora de IMC - Documentação

---

## English

### Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Project Structure](#project-structure)
4. [Components](#components)
5. [Models](#models)
6. [Styling](#styling)
7. [How to Use](#how-to-use)
8. [BMI Categories](#bmi-categories)
9. [Technologies Used](#technologies-used)
10. [Author](#author)

### Project Overview

**IMC Calculator** is a JavaFX-based application that calculates and displays the Body Mass Index (BMI) of a person. This project was created as an educational tool to teach Java and Object-Oriented Programming (OOP) principles.

The application provides a user-friendly interface where users can input their height and weight, and receive immediate feedback about their BMI category with visual indicators.

### Architecture

The project follows the **Model-View-Controller (MVC)** pattern with a component-based structure:

- **Model**: Contains data structures and enumerations (`Icons.java`)
- **View**: JavaFX components that compose the UI (`Header`, `Form`, `Card`, `CardContainer`, `Result`)
- **Controller**: Logic for handling user interactions and calculations (`Form.java`, `App.java`)

### Project Structure

```
imc-calculator/
├── src/
│   ├── module-info.java
│   └── com/github/imccalculator/
│       ├── app/
│       │   ├── App.java                 # Main application class
│       │   ├── components/
│       │   │   ├── Header.java          # Application header
│       │   │   ├── Form.java            # Input form component
│       │   │   ├── Card.java            # Reusable card component
│       │   │   ├── CardContainer.java   # Container for BMI category cards
│       │   │   └── Result.java          # Result display component
│       │   ├── models/
│       │   │   └── Icons.java           # Icon enumeration
│       │   └── resources/
│       │       └── style.css            # Application styling
│       └── resources/
│           └── style.css
└── bin/
    └── com/github/imccalculator/
        ├── app/
        │   ├── components/
        │   └── models/
        └── resources/
```

### Components

#### 1. **App.java**
The main entry point of the application. It:
- Extends `JavaFX.application.Application`
- Sets up the primary stage (window)
- Creates the main layout using `BorderPane`
- Loads CSS stylesheets
- Orchestrates the connection between components

**Key Methods:**
- `start(Stage primaryStage)`: Initializes the UI
- `main(String[] args)`: Application entry point

#### 2. **Header.java**
Displays the application header with the title and branding.

**Features:**
- Shows "IMC" icon with styling
- Displays "powered by JavaFX" subtitle
- Uses `HBox` layout for horizontal alignment
- Positioned at the top of the application

#### 3. **Form.java**
The input form component where users enter their data.

**Features:**
- Title: "Calcule seu IMC" (Calculate your BMI)
- Instructions explaining what IMC is
- Two text fields:
  - Height input (in meters, e.g., 1.80)
  - Weight input (in kilograms, e.g., 78.2)
- Two buttons:
  - "Calcular IMC" (Calculate BMI) - initiates calculation
  - "Cancelar" (Cancel) - clears the form

**Key Methods:**
- `loadActionsOn(Stage stage, Scene scene, String css)`: Sets up event handlers
  - Parses user input
  - Calculates BMI using formula: `BMI = weight / (height × height)`
  - Displays appropriate result based on BMI category

#### 4. **Card.java**
A reusable component for displaying information in card format.

**Constructors:**
1. `Card(String title, String text)` - Simple card with title and text
2. `Card(Icons imgUrl, String title, String text)` - Card with icon, title, and text

**Features:**
- Centered alignment
- Customizable styling via CSS classes
- Supports icon display with automatic sizing (70×70 pixels)

#### 5. **CardContainer.java**
A grid-based container displaying BMI categories.

**Displays 4 Categories:**
1. **Low Weight** (IMC 0-18.4) - Blue background
2. **Normal** (IMC 18.5-25) - Green background
3. **Overweight** (IMC 25-30) - Yellow background
4. **Obesity** (IMC 30+) - Red background

**Layout:**
- 2×2 GridPane layout
- Each card shows category name, range, and icon
- Right-aligned positioning

#### 6. **Result.java**
Displays the BMI calculation result with visual feedback.

**Features:**
- Shows calculated BMI value (formatted to 2 decimal places)
- Displays status message based on category
- Shows appropriate icon (warning, success, or error)
- "Calcular novamente" (Calculate Again) button to return to form
- Dynamic styling based on BMI category

### Models

#### Icons.java
An enumeration containing URLs for status icons.

**Values:**
- `WARNING`: Warning icon for underweight and overweight
- `SUCCESS`: Success icon for normal weight
- `ERROR`: Error icon for obesity

Each icon is loaded from external CDN resources as PNG images.

### Styling

The application uses JavaFX CSS (`style.css`) for all visual styling.

**Color Scheme:**
- **Primary**: Purple (`#71f`)
- **Secondary**: Green (`#1f7`)
- **Background**: Dark gray (`#1b1b1b`)
- **Low Weight**: Light blue (`#bdd9de`)
- **Normal**: Dark green (`#336d4c`)
- **Overweight**: Gold/Yellow (`#c9a821`)
- **Obesity**: Red (`#e35e5e`)

**Key Style Classes:**
- `.container`: Main application container
- `.form-title`: Form heading
- `.field`: Text input fields
- `.button`: Action buttons
- `.card`: Card components
- `.card-title`: Card headings
- `.card-text`: Card content text

### How to Use

1. **Launch the Application**
   - Run `App.java` to start the application

2. **Enter Your Data**
   - Input your height in meters (use comma or dot as decimal separator)
   - Input your weight in kilograms

3. **Calculate**
   - Click "Calcular IMC" button
   - The application displays your BMI category with visual feedback

4. **Review Result**
   - See your calculated BMI value
   - Understand your category status
   - Click "Calcular novamente" to perform another calculation

**Input Validation Note:**

- The current `Form` implementation assumes valid numeric input and will throw a `NumberFormatException` if fields are empty or contain invalid characters. Consider validating input before parsing. Example validation:

```java
if (fieldHeight.getText().isEmpty() || fieldWeight.getText().isEmpty()) {
   // show error to user and return
}
double height = Double.parseDouble(fieldHeight.getText().replace(",", "."));
double weight = Double.parseDouble(fieldWeight.getText().replace(",", "."));
```

**Styling/CSS Note:**

- If styles are not applied at runtime, verify that the `CSS_URL` constant in `App.java` points to the correct `style.css` file (use a `file:///absolute/path/to/style.css`).

### BMI Categories

| Category | BMI Range | Status |
|----------|-----------|--------|
| Underweight | < 18.5 | Below normal weight |
| Normal Weight | 18.5 - 25 | Healthy weight |
| Overweight | 25 - 30 | Above normal weight |
| Obesity | ≥ 30 | Obese |

### Technologies Used

- **Language**: Java 11+
- **UI Framework**: JavaFX
- **Build Tool**: Java Compiler (javac)
- **Module System**: Java 9+ Modules
- **Styling**: CSS

### Author

**João Gabriel Valentim Theodoro**
- Version: 1.0
- Created as an educational project for Java and OOP learning

---

## Português

### Sumário
1. [Visão Geral do Projeto](#visão-geral-do-projeto)
2. [Arquitetura](#arquitetura)
3. [Estrutura do Projeto](#estrutura-do-projeto)
4. [Componentes](#componentes)
5. [Modelos](#modelos)
6. [Estilização](#estilização)
7. [Como Usar](#como-usar)
8. [Categorias de IMC](#categorias-de-imc)
9. [Tecnologias Utilizadas](#tecnologias-utilizadas)
10. [Autor](#autor)

### Visão Geral do Projeto

A **Calculadora de IMC** é uma aplicação baseada em JavaFX que calcula e exibe o Índice de Massa Corpórea (IMC) de uma pessoa. Este projeto foi criado como uma ferramenta educacional para ensinar Java e princípios de Programação Orientada a Objetos (POO).

A aplicação oferece uma interface amigável onde os usuários podem inserir sua altura e peso, e receber feedback imediato sobre sua categoria de IMC com indicadores visuais.

### Arquitetura

O projeto segue o padrão **Model-View-Controller (MVC)** com estrutura baseada em componentes:

- **Model**: Contém estruturas de dados e enumerações (`Icons.java`)
- **View**: Componentes JavaFX que compõem a UI (`Header`, `Form`, `Card`, `CardContainer`, `Result`)
- **Controller**: Lógica para manipular interações do usuário e cálculos (`Form.java`, `App.java`)

### Estrutura do Projeto

```
imc-calculator/
├── src/
│   ├── module-info.java
│   └── com/github/imccalculator/
│       ├── app/
│       │   ├── App.java                 # Classe principal da aplicação
│       │   ├── components/
│       │   │   ├── Header.java          # Cabeçalho da aplicação
│       │   │   ├── Form.java            # Componente de formulário
│       │   │   ├── Card.java            # Componente de cartão reutilizável
│       │   │   ├── CardContainer.java   # Contêiner para cartões de categoria
│       │   │   └── Result.java          # Componente de exibição de resultado
│       │   ├── models/
│       │   │   └── Icons.java           # Enumeração de ícones
│       │   └── resources/
│       │       └── style.css            # Estilização da aplicação
│       └── resources/
│           └── style.css
└── bin/
    └── com/github/imccalculator/
        ├── app/
        │   ├── components/
        │   └── models/
        └── resources/
```

### Componentes

#### 1. **App.java**
O ponto de entrada principal da aplicação. É responsável por:
- Estender `JavaFX.application.Application`
- Configurar o estágio primário (janela)
- Criar o layout principal usando `BorderPane`
- Carregar folhas de estilo CSS
- Orquestrar a conexão entre componentes

**Métodos Principais:**
- `start(Stage primaryStage)`: Inicializa a UI
- `main(String[] args)`: Ponto de entrada da aplicação

#### 2. **Header.java**
Exibe o cabeçalho da aplicação com o título e marca.

**Recursos:**
- Mostra ícone "IMC" com estilização
- Exibe subtítulo "powered by JavaFX"
- Usa layout `HBox` para alinhamento horizontal
- Posicionado no topo da aplicação

#### 3. **Form.java**
O componente de formulário de entrada onde os usuários inserem seus dados.

**Recursos:**
- Título: "Calcule seu IMC"
- Instruções explicando o que é IMC
- Dois campos de texto:
  - Entrada de altura (em metros, ex: 1,80)
  - Entrada de peso (em quilogramas, ex: 78,2)
- Dois botões:
  - "Calcular IMC" - inicia o cálculo
  - "Cancelar" - limpa o formulário

**Métodos Principais:**
- `loadActionsOn(Stage stage, Scene scene, String css)`: Configura os manipuladores de eventos
  - Analisa entrada do usuário
  - Calcula IMC usando fórmula: `IMC = peso / (altura × altura)`
  - Exibe o resultado apropriado com base na categoria de IMC

#### 4. **Card.java**
Um componente reutilizável para exibir informações em formato de cartão.

**Construtores:**
1. `Card(String title, String text)` - Cartão simples com título e texto
2. `Card(Icons imgUrl, String title, String text)` - Cartão com ícone, título e texto

**Recursos:**
- Alinhamento centralizado
- Estilização personalizável via classes CSS
- Suporta exibição de ícones com dimensionamento automático (70×70 pixels)

#### 5. **CardContainer.java**
Um contêiner baseado em grade que exibe as categorias de IMC.

**Exibe 4 Categorias:**
1. **Abaixo do peso** (IMC 0-18.4) - Fundo azul
2. **Normal** (IMC 18.5-25) - Fundo verde
3. **Sobrepeso** (IMC 25-30) - Fundo amarelo
4. **Obesidade** (IMC 30+) - Fundo vermelho

**Layout:**
- Layout GridPane 2×2
- Cada cartão mostra nome da categoria, intervalo e ícone
- Alinhado à direita

#### 6. **Result.java**
Exibe o resultado do cálculo de IMC com feedback visual.

**Recursos:**
- Mostra valor de IMC calculado (formatado com 2 casas decimais)
- Exibe mensagem de status com base na categoria
- Mostra ícone apropriado (aviso, sucesso ou erro)
- Botão "Calcular novamente" para retornar ao formulário
- Estilização dinâmica com base na categoria de IMC

### Modelos

#### Icons.java
Uma enumeração contendo URLs para ícones de status.

**Valores:**
- `WARNING`: Ícone de aviso para baixo peso e sobrepeso
- `SUCCESS`: Ícone de sucesso para peso normal
- `ERROR`: Ícone de erro para obesidade

Cada ícone é carregado de recursos PNG em CDN externo.

### Estilização

A aplicação utiliza CSS do JavaFX (`style.css`) para toda a estilização visual.

**Paleta de Cores:**
- **Primária**: Roxo (`#71f`)
- **Secundária**: Verde (`#1f7`)
- **Fundo**: Cinza escuro (`#1b1b1b`)
- **Abaixo do peso**: Azul claro (`#bdd9de`)
- **Normal**: Verde escuro (`#336d4c`)
- **Sobrepeso**: Ouro/Amarelo (`#c9a821`)
- **Obesidade**: Vermelho (`#e35e5e`)

**Classes de Estilo Principais:**
- `.container`: Contêiner principal da aplicação
- `.form-title`: Título do formulário
- `.field`: Campos de entrada de texto
- `.button`: Botões de ação
- `.card`: Componentes de cartão
- `.card-title`: Títulos de cartões
- `.card-text`: Texto de conteúdo de cartões

### Como Usar

1. **Inicie a Aplicação**
   - Execute `App.java` para iniciar a aplicação

2. **Insira Seus Dados**
   - Digite sua altura em metros (use vírgula ou ponto como separador decimal)
   - Digite seu peso em quilogramas

3. **Calcule**
   - Clique no botão "Calcular IMC"
   - A aplicação exibe sua categoria de IMC com feedback visual

4. **Revise o Resultado**
   - Veja seu valor de IMC calculado
   - Compreenda seu status de categoria
   - Clique em "Calcular novamente" para realizar outro cálculo

### Categorias de IMC

| Categoria | Intervalo de IMC | Status |
|-----------|------------------|--------|
| Abaixo do peso | < 18.5 | Peso abaixo do normal |
| Peso Normal | 18.5 - 25 | Peso saudável |
| Sobrepeso | 25 - 30 | Peso acima do normal |
| Obesidade | ≥ 30 | Obeso |

### Tecnologias Utilizadas

- **Linguagem**: Java 11+
- **Framework de UI**: JavaFX
- **Ferramenta de Build**: Java Compiler (javac)
- **Sistema de Módulos**: Java 9+ Modules
- **Estilização**: CSS

### Autor

**João Gabriel Valentim Theodoro**
- Versão: 1.0
- Criado como projeto educacional para aprendizado de Java e POO

---

## Additional Resources | Recursos Adicionais

### Running the Application | Executando a Aplicação

```bash
# Compile
javac --module-source-path src -d bin -m com.github.imccalculator

# Run
java --module-path bin -m com.github.imccalculator/com.github.imccalculator.app.App
```

### BMI Formula | Fórmula de IMC

$$BMI = \frac{weight (kg)}{height (m)^2}$$

### Dependencies | Dependências

- JavaFX SDK (Required for UI)
- Java Runtime Environment (JRE) 11+

---

**Last Updated**: December 10, 2025
**Última Atualização**: 10 de dezembro de 2025
