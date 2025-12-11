# Technical Guide | Guia Técnico
## IMC Calculator - Complete Technical Documentation

---

## English

### Table of Contents
1. [System Requirements](#system-requirements)
2. [Installation Guide](#installation-guide)
3. [Component Documentation](#component-documentation)
4. [Code Examples](#code-examples)
5. [Troubleshooting](#troubleshooting)
6. [Performance Considerations](#performance-considerations)
7. [Future Enhancements](#future-enhancements)

---

### System Requirements

#### Minimum Requirements
- **Java Version**: JDK 11 or higher
- **RAM**: 512 MB minimum
- **Storage**: 100 MB (including JavaFX)
- **OS**: Windows, macOS, or Linux with graphical interface

#### Recommended Requirements
- **Java Version**: JDK 17 LTS or higher
- **RAM**: 2 GB or more
- **JavaFX SDK**: Latest version (20+)
- **IDE**: IntelliJ IDEA or Eclipse (optional, for development)

#### Required Libraries
```xml
<!-- JavaFX (must be manually downloaded from gluonhq.com) -->
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>20.0.1</version>
</dependency>
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-graphics</artifactId>
    <version>20.0.1</version>
</dependency>
```

### Installation Guide

#### Step 1: Download and Install JDK

1. Visit [oracle.com/java](https://www.oracle.com/java/technologies/downloads/)
2. Download JDK 11 or higher for your OS
3. Install and verify:
   ```bash
   java -version
   javac -version
   ```

#### Step 2: Set Up JavaFX

1. Download JavaFX SDK from [gluonhq.com](https://gluonhq.com/products/javafx/)
2. Extract to a known location
3. Note the path for later use

#### Step 3: Clone/Download Project

```bash
git clone <repository-url>
cd imc-calculator
```

#### Step 4: Compile the Project

```bash
# Option 1: Basic compilation
javac --module-source-path src -d bin -m com.github.imccalculator

# Option 2: With JavaFX library path (if needed)
javac --module-path /path/to/javafx-sdk/lib \
      --add-modules javafx.controls,javafx.graphics \
      --module-source-path src \
      -d bin \
      -m com.github.imccalculator
```

#### Step 5: Run the Application

```bash
# Option 1: Basic run
java --module-path bin \
     -m com.github.imccalculator/com.github.imccalculator.app.App

# Option 2: With JavaFX library path (if needed)
java --module-path /path/to/javafx-sdk/lib:bin \
     --add-modules javafx.controls,javafx.graphics \
     -m com.github.imccalculator/com.github.imccalculator.app.App

#### Example: macOS (JavaFX SDK on module path)

You can export a `PATH_TO_FX` variable and run the app including JavaFX modules. Replace `/path/to/javafx-sdk/lib` with your SDK path:

```bash
export PATH_TO_FX="/path/to/javafx-sdk/lib"
javac --module-path "$PATH_TO_FX" --add-modules javafx.controls,javafx.graphics \
    --module-source-path src -d bin -m com.github.imccalculator

java --module-path "$PATH_TO_FX:bin" --add-modules javafx.controls,javafx.graphics \
   -m com.github.imccalculator/com.github.imccalculator.app.App
```

**Note:** If styling is missing, update `CSS_URL` in `App.java` to the absolute `file:///` path to `style.css`.
```

---

### Component Documentation

#### 1. App.java - Main Application Class

**Package**: `com.github.imccalculator.app`

**Inheritance**: `extends javafx.application.Application`

**Purpose**: Entry point and orchestrator of the application

**Key Methods**:

```java
@Override
public void start(Stage primaryStage) throws Exception
```
- **Parameters**:
  - `primaryStage` (Stage): The primary window container
- **Functionality**:
  - Creates BorderPane layout container
  - Instantiates Header, Form, and CardContainer components
  - Sets window properties (title, size)
  - Applies CSS stylesheet
  - Displays the scene

```java
public static void main(String[] args)
```
- **Functionality**: 
  - Application entry point
  - Calls `launch(args)` to start JavaFX application

**Important Variables**:
```java
private static final String CSS_URL = "file:///.../style.css";
```
- Path to external CSS stylesheet
- Must be updated based on actual file location

**Layout Structure**:
```
BorderPane (Root)
├── Top: Header (Title bar)
└── Center: HBox (Main content)
    ├── Form (Left side - Input)
    └── CardContainer (Right side - BMI Categories)
```

---

#### 2. Header.java - Application Header

**Package**: `com.github.imccalculator.app.components`

**Inheritance**: `extends javafx.scene.layout.HBox`

**Constructor**:
```java
public Header()
```

**Components Created**:
- `titleIcon` (Label): Displays "IMC" text
- `title` (Label): Displays "powered by JavaFX"

**Styling Applied**:
- `.icon`: Purple background with white text
- `.title`: Green text

**Layout**: Horizontal arrangement with 5px spacing, left-aligned

---

#### 3. Form.java - Input Form Component

**Package**: `com.github.imccalculator.app.components`

**Inheritance**: `extends javafx.scene.layout.VBox`

**UI Elements**:
```java
// Labels
private final Label formTitle;           // "Calcule seu IMC"
private final Label formInstructions;    // IMC explanation
```

```java
// Input Fields
private final TextField fieldHeight;     // Height input (meters)
private final TextField fieldWeight;     // Weight input (kilograms)
```

```java
// Buttons
private final Button calculateBMIButton;      // Calculate action
private final Button cancelCalculateBMIButton; // Clear action
private final HBox buttons;                   // Button container
```

**Key Method**:

```java
public void initializeActionHandlers(Stage stage, Scene scene, String css)
```

**Functionality**:
1. **Extract Input**:
   ```java
   var height = Double.parseDouble(fieldHeight.getText().replace(",", "."));
   var weight = Double.parseDouble(fieldWeight.getText().replace(",", "."));
   ```
   - Converts comma to dot for Brazilian number format

2. **Calculate BMI**:
   ```java
   var bmi = weight / (height * height);
   ```

3. **Determine Category / Validation**:
   - `bmi < 18.5` → Low weight (WARNING icon)
   - `bmi >= 18.5 && bmi <= 25` → Normal (SUCCESS icon)
   - `bmi >= 25 && bmi < 30` → Overweight (WARNING icon)
   - `bmi >= 30` → Obesity (ERROR icon)

Before parsing, the updated form performs a simple empty-field validation. If either input is empty the form shows a validation `Result` (alert-only) and does not attempt parsing.

4. **Create and Display Result**:
   - Creates `Result` component with appropriate styling
   - Creates new Scene with Result
   - Switches to result scene

**Event Handlers**:
- `calculateBMIButton.setOnAction()`: Handles calculate action
- `cancelCalculateBMIButton.setOnAction()`: Handles cancel action

---

#### 4. Card.java - Reusable Card Component

**Package**: `com.github.imccalculator.app.components`

**Inheritance**: `extends javafx.scene.layout.VBox`

**Constructors**:

**Constructor 1** - Simple Card:
```java
public Card(String title, String text)
```
- Creates card with title and text only

**Constructor 2** - Card with Icon:
```java
public Card(Icons imgUrl, String title, String text)
```
- Parameters:
  - `imgUrl` (Icons): Enumeration value for icon URL
  - `title` (String): Card title
  - `text` (String): Card description

**Icon Processing** (in Constructor 2):
```java
Image image = new Image(imgUrl.getIconUrl());
ImageView view = new ImageView(image);
view.setPreserveRatio(true);
view.setFitHeight(70);
view.setFitWidth(70);
```
- Loads image from URL
- Sets fixed size to 70×70 pixels
- Maintains aspect ratio

**Styling**:
- Applied class: `.card`
- Content classes: `.card-title`, `.card-text`
- Spacing: 20px between elements
- Alignment: Centered

---

#### 5. CardContainer.java - BMI Category Grid

**Package**: `com.github.imccalculator.app.components`

**Inheritance**: `extends javafx.scene.layout.GridPane`

**Components Created**:

```java
Card low = new Card(Icons.WARNING, "Abaixo do peso", "IMC de 0 a 18.4");
Card normal = new Card(Icons.SUCCESS, "Normal", "IMC de 18.5 a 25");
Card high = new Card(Icons.WARNING, "Sobrepeso", "IMC de 25 a 30");
Card varyHigh = new Card(Icons.ERROR, "Obesidade", "IMC de 30 e superior");
```

**Grid Layout** (2×2):
```
┌─────────────────┬─────────────────┐
│   low (0,0)     │  normal (1,0)   │
├─────────────────┼─────────────────┤
│   high (0,1)    │ varyHigh (1,1)  │
└─────────────────┴─────────────────┘
```

**Grid Properties**:
- Horizontal gap: 20px
- Vertical gap: 20px
- Right alignment
- Style class: `.card-box`

**Styling by Category**:
- `.low`: Light blue background (#bdd9de)
- `.normal`: Dark green background (#336d4c)
- `.high`: Gold/yellow background (#c9a821)
- `.vary-high`: Red background (#e35e5e)

---

#### 6. Result.java - Result Display Component

**Package**: `com.github.imccalculator.app.components`

**Inheritance**: `extends javafx.scene.layout.VBox`

**Constructor**:
```java
public Result(double bmi, String alertText, Icons icon, 
              String classCss, Stage stage, Scene main)
```

**Parameters**:
- `bmi` (double): Calculated BMI value
- `alertText` (String): Status message ("Peso normal", "Sobrepeso", etc.)
- `icon` (Icons): Icon enumeration for display
- `classCss` (String): CSS class name for styling ("low", "normal", "high", "vary-high")
- `stage` (Stage): Reference to primary window
- `main` (Scene): Reference to main form scene

**UI Elements Created**:
```java
ImageView view;              // Icon display
Label resultTextLabel;       // BMI value (formatted to 2 decimals)
Label alertTextLabel;        // Status message
Button back;                 // "Calcular novamente" button
```

**BMI Formatting**:
```java
String.format("Seu IMC é %.2f", bmi)
```
- Displays: "Seu IMC é 24.35"

**Navigation**:
```java
back.setOnAction(e -> {
    stage.setScene(main);
});
```
- Returns to form scene when "Calcular novamente" is clicked

**Styling**:
- Dynamic class: Applied from `classCss` parameter
- Standard classes: `.card-text` for labels

---

#### 7. Icons.java - Icon Enumeration

**Package**: `com.github.imccalculator.app.models`

**Type**: Java Enumeration

**Enum Values**:

```java
WARNING("https://static.vecteezy.com/.../warning-sign-icon-...png")
SUCCESS("https://cdn-icons-png.flaticon.com/.../7518748.png")
ERROR("https://www.freeiconspng.com/.../alert-icon-red-11.png")
```

**Methods**:
```java
public String getIconUrl()
```
- Returns the URL string for the icon image

**Icon Sources**:
- All icons loaded from external CDN URLs
- PNG format with transparent backgrounds
- Suitable for use with ImageView component

---

### Code Examples

#### Example 1: Creating a Custom Card

```java
// Simple card
Card customCard = new Card("My Title", "My description");
customCard.getStyleClass().add("custom-style");

// Card with icon
Card iconCard = new Card(Icons.SUCCESS, "Success Title", "Success message");
customCard.getStyleClass().add("custom-style");

#### Example: Using the `Bmi` model from `Form`

```java
// inside Form.initializeActionHandlers
double height = Double.parseDouble(fieldHeight.getText().replace(",", "."));
double weight = Double.parseDouble(fieldWeight.getText().replace(",", "."));
Bmi bmi = new Bmi(height, weight);
bmi.showBmiResult(stage, scene, css);
```
```

#### Example 2: Extending the Application

```java
public class ExtendedApp extends App {
    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
        
        // Add custom logic
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("Application closing");
        });
    }
}
```

#### Example 3: Creating New BMI Categories

```java
// Add to CardContainer constructor
Card underweightExtreme = new Card(
    Icons.ERROR, 
    "Severely Underweight", 
    "IMC < 16"
);
underweightExtreme.getStyleClass().add("extreme-low");

add(underweightExtreme, 0, 2);
```

#### Example 4: Custom Styling

```css
/* Custom style extension */
.card {
    -fx-background-color: #17f;
    -fx-padding: 20px;
    -fx-border-radius: 10;
}

.custom-style {
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 2);
}
```

---

### Troubleshooting

#### Issue 1: "Module not found" Error

**Symptom**:
```
Error: Could not find or load main class com.github.imccalculator.app.App
```

**Solutions**:
1. Ensure `module-info.java` exists in src directory
2. Check module name in commands matches actual module
3. Verify compilation completed successfully:
   ```bash
   ls -la bin/com/github/imccalculator/app/
   ```

#### Issue 2: CSS File Not Found

**Symptom**:
```
CSS file not loading, no styling applied
```

**Solutions**:
1. Update CSS_URL path in App.java:
   ```java
   private static final String CSS_URL = "file:///{YOUR_ABSOLUTE_PATH}/style.css";
   ```
2. Use proper file URI format (file:///)
3. Use absolute paths instead of relative paths

#### Issue 3: JavaFX Not Found

**Symptom**:
```
Error: JavaFX runtime components are missing
```

**Solutions**:
1. Download JavaFX SDK from gluonhq.com
2. Set module path in runtime:
   ```bash
   java --module-path /path/to/javafx/lib \
        --add-modules javafx.controls,javafx.graphics \
        -m com.github.imccalculator/com.github.imccalculator.app.App
   ```

#### Issue 4: Number Format Exception

**Symptom**:
```
Exception in thread "JavaFX Application Thread"
java.lang.NumberFormatException: For input string: ""
```

**Solutions**:
1. Ensure both fields have valid input before clicking calculate
2. Check decimal separator (comma vs dot)
3. Add input validation:
   ```java
   if (fieldHeight.getText().isEmpty() || fieldWeight.getText().isEmpty()) {
       System.out.println("Please fill all fields");
       return;
   }
   ```

---

### Performance Considerations

#### Memory Usage
- **Initial Memory**: ~100 MB
- **Per Scene Change**: ~5-10 MB
- **Image Loading**: 2-3 MB per icon (from CDN)

#### Optimization Tips

1. **Cache Icons**:
   ```java
   private static final Map<Icons, Image> imageCache = new HashMap<>();
   
   private Image getCachedImage(Icons icon) {
       return imageCache.computeIfAbsent(icon, 
           i -> new Image(i.getIconUrl()));
   }
   ```

2. **Lazy Load Components**:
   ```java
   private Result result;
   
   public Result getResult(double bmi, ...) {
       if (result == null) {
           result = new Result(bmi, ...);
       }
       return result;
   }
   ```

3. **Input Validation**:
   ```java
   if (height <= 0 || weight <= 0) {
       showError("Invalid input values");
       return;
   }
   ```

---

### Future Enhancements

#### Suggested Improvements

1. **Database Integration**
   - Store user history
   - Track BMI progress over time

2. **Additional Features**
   - Multi-language support (English, Spanish, etc.)
   - BMI recommendation suggestions
   - Health tips based on BMI category

3. **Advanced UI**
   - Charts for BMI history
   - User profile management
   - Dark/Light theme toggle

4. **API Integration**
   - Integration with health APIs
   - Sync with fitness trackers
   - Cloud backup of user data

5. **Mobile Version**
   - JavaFX Mobile compatibility
   - Android/iOS ports using frameworks like React Native

---

## Português

### Sumário
1. [Requisitos do Sistema](#requisitos-do-sistema-pt)
2. [Guia de Instalação](#guia-de-instalação-pt)
3. [Documentação de Componentes](#documentação-de-componentes-pt)
4. [Exemplos de Código](#exemplos-de-código-pt)
5. [Solução de Problemas](#solução-de-problemas-pt)
6. [Considerações de Desempenho](#considerações-de-desempenho-pt)
7. [Melhorias Futuras](#melhorias-futuras-pt)

---

### Requisitos do Sistema (PT)

#### Requisitos Mínimos
- **Versão Java**: JDK 11 ou superior
- **RAM**: 512 MB mínimo
- **Armazenamento**: 100 MB (incluindo JavaFX)
- **SO**: Windows, macOS ou Linux com interface gráfica

#### Requisitos Recomendados
- **Versão Java**: JDK 17 LTS ou superior
- **RAM**: 2 GB ou mais
- **JavaFX SDK**: Versão mais recente (20+)
- **IDE**: IntelliJ IDEA ou Eclipse (opcional, para desenvolvimento)

---

### Guia de Instalação (PT)

#### Passo 1: Baixar e Instalar JDK

1. Visite [oracle.com/java](https://www.oracle.com/java/technologies/downloads/)
2. Baixe JDK 11 ou superior para seu SO
3. Instale e verifique:
   ```bash
   java -version
   javac -version
   ```

#### Passo 2: Configurar JavaFX

1. Baixe JavaFX SDK em [gluonhq.com](https://gluonhq.com/products/javafx/)
2. Extraia para uma localização conhecida
3. Anote o caminho para usar depois

#### Passo 3: Clonar/Baixar Projeto

```bash
git clone <url-repositório>
cd imc-calculator
```

#### Passo 4: Compilar o Projeto

```bash
# Opção 1: Compilação básica
javac --module-source-path src -d bin -m com.github.imccalculator

# Opção 2: Com caminho da biblioteca JavaFX (se necessário)
javac --module-path /caminho/para/javafx-sdk/lib \
      --add-modules javafx.controls,javafx.graphics \
      --module-source-path src \
      -d bin \
      -m com.github.imccalculator
```

#### Passo 5: Executar a Aplicação

```bash
# Opção 1: Execução básica
java --module-path bin \
     -m com.github.imccalculator/com.github.imccalculator.app.App

# Opção 2: Com caminho da biblioteca JavaFX (se necessário)
java --module-path /caminho/para/javafx-sdk/lib:bin \
     --add-modules javafx.controls,javafx.graphics \
     -m com.github.imccalculator/com.github.imccalculator.app.App
```

---

### Documentação de Componentes (PT)

#### Fluxo de Execução Principal

1. **App.java** inicia a aplicação
2. **Header** exibe o título
3. **Form** recebe entrada do usuário
4. **CardContainer** mostra categorias de IMC
5. Ao clicar "Calcular IMC":
   - Form valida entrada
   - Calcula IMC usando a fórmula: `IMC = peso / (altura²)`
   - Cria component Result apropriado
   - Troca para cena de resultado
6. **Result** exibe valor e categoria
7. Botão "Calcular novamente" retorna ao Form

#### Estrutura de Classes

```
├── App (extends Application)
│   ├── Header (extends HBox)
│   ├── Form (extends VBox)
│   │   └── loadActionsOn()
│   ├── CardContainer (extends GridPane)
│   │   └── Card[] (4 cards)
│   └── Result (extends VBox)
│
├── Icons (enum)
│   ├── WARNING
│   ├── SUCCESS
│   └── ERROR
│
└── style.css
```

---

### Exemplos de Código (PT)

#### Exemplo 1: Adicionar Validação de Input

```java
private boolean validateInputs() {
    String heightText = fieldHeight.getText().trim();
    String weightText = fieldWeight.getText().trim();
    
    if (heightText.isEmpty() || weightText.isEmpty()) {
        System.out.println("Por favor, preencha todos os campos");
        return false;
    }
    
    try {
        double height = Double.parseDouble(heightText.replace(",", "."));
        double weight = Double.parseDouble(weightText.replace(",", "."));
        
        if (height <= 0 || weight <= 0) {
            System.out.println("Altura e peso devem ser maiores que zero");
            return false;
        }
        return true;
    } catch (NumberFormatException e) {
        System.out.println("Formato de número inválido");
        return false;
    }
}
```

#### Exemplo 2: Estender Categorias de IMC

```java
// Adicionar ao CardContainer
if (bmi < 16) {
    Card severelyUnderweight = new Card(
        Icons.ERROR,
        "Severamente Abaixo do Peso",
        "IMC < 16"
    );
    add(severelyUnderweight, 0, 2);
}
```

---

### Solução de Problemas (PT)

#### Problema 1: Erro "Módulo não encontrado"

**Solução**:
1. Verifique se `module-info.java` existe em src
2. Confirme nome do módulo nos comandos
3. Verifique compilação: `ls -la bin/com/github/imccalculator/app/`

#### Problema 2: Arquivo CSS Não Carrega

**Solução**:
1. Atualize caminho CSS_URL em App.java
2. Use formato correto de arquivo URI (file:///)
3. Use caminhos absolutos

#### Problema 3: JavaFX Não Encontrado

**Solução**:
1. Baixe JavaFX SDK de gluonhq.com
2. Configure module path na execução

#### Problema 4: Exceção NumberFormatException

**Solução**:
1. Implemente validação de input
2. Verifique separador decimal (vírgula vs ponto)
3. Garanta que campos não estejam vazios

---

### Considerações de Desempenho (PT)

#### Uso de Memória
- **Memória Inicial**: ~100 MB
- **Por Mudança de Cena**: ~5-10 MB
- **Carregamento de Imagem**: 2-3 MB por ícone

#### Dicas de Otimização

1. **Cache de Ícones**: Armazene imagens em memória
2. **Carregamento Lazy**: Carregue componentes sob demanda
3. **Validação de Input**: Valide antes de processar

---

### Melhorias Futuras (PT)

#### Funcionalidades Sugeridas

1. **Integração de Banco de Dados**
   - Armazenar histórico de usuário
   - Acompanhar progresso de IMC ao longo do tempo

2. **Recursos Adicionais**
   - Suporte multilíngue
   - Sugestões de recomendações de saúde
   - Dicas baseadas em categoria de IMC

3. **Interface Avançada**
   - Gráficos de histórico de IMC
   - Gerenciamento de perfil de usuário
   - Alternância de tema escuro/claro

4. **Integração com API**
   - Integração com APIs de saúde
   - Sincronização com rastreadores de fitness
   - Backup em nuvem de dados do usuário

5. **Versão Mobile**
   - Compatibilidade JavaFX Mobile
   - Portas para Android/iOS

---

**Document Version**: 1.0
**Last Updated**: December 10, 2025
**Versão do Documento**: 1.0
**Última Atualização**: 10 de dezembro de 2025
