import { Component } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-video',
  template: `
    <iframe
      width="560"
      height="315"
      src="https://www.youtube.com/embed/0Yx0kgFDPaY?si=Y1eOs8EvmtiJ62P4"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      allowfullscreen
    ></iframe>
  `,
  styles: [
    `
      iframe {
        width: 100%;
        height: 100%;
        border: none;
      }
    `,
  ],
})
export class VideoComponent {}
