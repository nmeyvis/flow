# Flow

Flow is a templating language for your everyday life. Instead of looking things up during conversation, simply continue
your thought flow with templates.

Consider a conversation in your Australian friend:

> *YOU*: Today I bought a cool hat at the store.

> *Australian*: For how much?

> *YOU*: It was only $10

Confusion arises as your friend wonders if you meant USD or AUD.
Maybe you're a good friend and you'll do the conversion for him because he prefers AUD.
Instead of opening your web browser and googling the conversion, you can simply use templates while you type.

> *Australian*: For how much?

> *YOU*: It was only `$$:aud:10`

By pressing the program hotkey `CTRL + ALT + F`, your message will be replaced with the correct conversion:

> It was only $12.95 (AUD)

Then your message is ready to send.

Flow works on any application.


## Usage

Templates start with `$`, then you specify parameters and terminate with a pipe `|`. If your template is at
the end of your message, you can omit the pipe.

Pressing the hotkey `CTRL + ALT + F` will replace your text with the evaluted output.

## Download

You can download the latest binaries from the [releases](https://github.com/nmeyvis/flow/releases) page. Extract the zip, and run the provided batch
to start Flow.


## Current Addons

### Currency

Syntax:
`$$:<currency code>:<amount>`

Example:
> I bought some eggs for `$$:aud:3.50`

Output:
> I bought some eggs for $4.63 (AUD)

[Currency Codes are available here](https://en.wikipedia.org/wiki/ISO_4217#Active_codes)

### Translate
Translate your text using Google Translate.

Syntax:
`$lang:<target language code>:<your text to convert>`

Example:
`$lang:fr:Hello, my name is Jim, how are you?`

Output:
> Bonjour, mon nom est Jim, comment allez-vous?

[Language codes are available here](https://cloud.google.com/translate/docs/languages)

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
| smug      | (‾⌣‾)             |
| gimme     | ༼つ ◕_◕ ༽つ       |
| stroll    | ᕕ(ᐛ )ᕗ            |
| power     | ᕦ(ò_óˇ)ᕤ          |
| lenny     | (͡° ͜ʖ ͡°)           |

## Requirements
+ Java 8
+ Internet connection for some addons

## Future

Flow is still highly WIP and is mostly a prototype at the moment.

* Expand addon API: downloading, loading, managing external addons
* Create more addons (contacts, unit conversion, bookmarks, etc)
* Public addon repository
* Make a more natural templating / query language
* Possibly add a GUI alternative to the language
* User configuration (custom hot keys, etc)
* Native installers
* Website
