/* @flow */

import memoize from 'fast-memoize';
import convert from 'color-convert';
import supportsColor from 'supports-color';
import { create as createRgb } from './rgb';

export function create(level: number) {
  const { withRgbColor, withRgbBackgroundColor } = createRgb(level);
  return {
    withKeywordColor: memoize((color: string, text: string) =>
      withRgbColor(convert.keyword.rgb(color), text)
    ),

    withKeywordBackgroundColor: memoize((color: string, text: string) =>
      withRgbBackgroundColor(convert.keyword.rgb(color), text)
    ),
  };
}

export const { withKeywordColor, withKeywordBackgroundColor } = create(
  process.env.CI ? 1 : supportsColor.stdout.level
);
