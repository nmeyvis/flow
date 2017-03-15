# Flow

Flow is a templating language for your everyday life. Instead of looking things up during conversation, simply continue
your thought flow with templates.

Consider a conversation in your Australian friend:

> *YOU*: Today I bought some cheese at the store.

> *Australian*: For how much?

> *YOU*: It was only $3.50

Confusion arrises as your friend wonders if you meant USD or AUD.
Maybe you're a good friend and you'll do the conversion for him because he prefers AUD.
Instead of opening your web browser and googling the conversion, you can simply use templates while you type.

> *Australian*: For how much?

> *YOU*: It was only `$$:aud:3.50`

By pressing the program hotkey `CTRL + ALT + F`, your message will be replaced with:

> It was only $4.629 (AUD)

Then your message is ready to send.

Flow works on any application.


## Usage

Templates start with `$`, then you specify parameters and terminate with a pipe `|`. If your template is at
the end of your message, you can omit the pipe.

Pressing the hotkey `CTRL + ALT + F` will replace your text with the evaluted output.

### Omitting the pipe example
> Hello, I bought some cheese today for `$$:aud:3.50|`. It was good.

> Hello, I bought some cheese today for `$$:aud:3.50`


## Current Addons

### Currency

Syntax:
`$$:<currency code>:<amount>`

Example:
> I bought some cheese for `$$:aud:3.50`

Output:
> I bought some cheese for $4.63 (AUD)

[Currency Codes are available here](https://en.wikipedia.org/wiki/ISO_4217#Active_codes)


### Meme
Syntax:
`$meme:<meme name>`

Example: 
`$meme:fliptable`

Output:
> (╯°□°）╯︵ ┻━┻

| Name      | Meme              |
|-----------|-------------------|
| fliptable | (╯°□°）╯︵ ┻━┻    |
| knife     | )xxxxx[;;;;;;;;;> |
| boobs     | (.)(.)            |
| smug      | (‾⌣‾)             |
| gimme     | ༼つ ◕_◕ ༽つ       |
| stroll    | ᕕ(ᐛ )ᕗ            |
| power     | ᕦ(ò_óˇ)ᕤ          |
| lenny     | (͡° ͜ʖ ͡°)           |


### Translate
Syntax: 
`$lang:<target language code>:<your text to convert>`

Example: 
`$lang:fr:hello, my name is Jim, how are you?`

Output: 
> Bonjour, mon nom est Jim, comment allez-vous?

[Language codes are available here](https://cloud.google.com/translate/docs/languages)
