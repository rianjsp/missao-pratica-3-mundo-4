# Doma Assistance App

Este é um aplicativo Android desenvolvido para dispositivos Wear OS, com foco em acessibilidade para funcionários com necessidades especiais, especialmente pessoas com deficiência visual. O app fornece assistência por áudio, leitura de mensagens, comandos de voz e envio de alertas importantes. Foi desenvolvido com foco em usabilidade, acessibilidade e integração com funcionalidades de voz do sistema Android.

## Funcionalidades

- **Leitura por voz**: Informa mensagens e tarefas para o usuário utilizando o mecanismo de Text-to-Speech (TTS).
- **Comando de voz**: Reconhece comandos de voz e executa ações como alertar ou informar tarefas.
- **Alertas por notificação**: Envia notificações com mensagens de segurança em tempo real.
- **Interface simples**: Botões intuitivos para iniciar comandos de voz, ler mensagens ou emitir alertas.
- **Verificação de dispositivos de áudio**: Identifica se há dispositivos de saída de áudio conectados ao sistema (ex: fone Bluetooth, alto-falantes).

## Libs utilizadas

- **Principal**:  
  - `Android SDK` — Ferramentas básicas para desenvolvimento Android.
  - `TextToSpeech` — Biblioteca nativa para síntese de voz.
  - `RecognizerIntent` — Biblioteca nativa para reconhecimento de fala.
  - `NotificationCompat` — Biblioteca para criação de notificações compatíveis com versões antigas do Android.
  - `AudioManager` — API nativa para gerenciamento de dispositivos de áudio.

## Componentes Principais

- **MainActivity (MainActivity.kt)**  
  Classe principal do app. Responsável por inicializar os botões, lidar com eventos de clique, sintetizar fala com TTS, receber comandos de voz e enviar notificações ao usuário.

- **AudioHelper (AudioHelper.kt)**  
  Classe auxiliar que verifica a disponibilidade de dispositivos de saída de áudio, como fones de ouvido ou alto-falantes Bluetooth, garantindo que o áudio seja direcionado corretamente.
